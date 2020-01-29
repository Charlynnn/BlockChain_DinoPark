pragma solidity ^0.6.1;

contract mortal {
    address payable owner;
    constructor() public { owner = msg.sender; }
}


contract miniCrowdfunding is mortal{

    struct Tier {
        int lower_bound;
        uint256 upper_bound;
        uint256 interest_rate;
        string merch_reward;
    }


    address[] grand_patrons;
    string public mini_crowdfunding_name;
    uint256 public investment_goal;
    uint256 public current_investment;
    uint public mini_crowdfunding_expiry;

    mapping (address => uint) public balances;

    Tier[] public tiers;
    Tier public  patrons_tier = Tier(5, 1, 0, "super exclusive figure");

    //Events

    event GoalReached(uint256 current_investment);
    event ProjectFunded(uint256 investment);
    event EthReceived(address investor_address, uint256 current_investment);


    constructor(address[] memory patrons, string memory name, uint expiry, uint256 goal) public{
        grand_patrons = patrons;
        mini_crowdfunding_name = name;
        investment_goal = goal;
        mini_crowdfunding_expiry = now + expiry;
        tiers.push(Tier(1, 10, 105,"no merch"));
        tiers.push(Tier(11, 25, 110,"no merch"));
        tiers.push(Tier(26, 1000000, 120, "exclusive keychain"));
    }

    function invest()  public payable {
        require(
            now <= mini_crowdfunding_expiry,
            "Crowdfunding already ended."
        );
        require(
            current_investment < investment_goal,
            "Goal already reached."
        );
        // check overflow of the balances
        address payable investor_address = msg.sender;
        uint256 eth_received = msg.value;

        current_investment += eth_received;
        balances[msg.sender] += eth_received;

        emit EthReceived(investor_address, current_investment);
        if(current_investment >= investment_goal)
            emit GoalReached(current_investment);
    }

    function commitFunding() public {
        require(
            now > mini_crowdfunding_expiry,
            "Crowdfunding hasn't ended yet"
        );
        require(
            current_investment >=  investment_goal,
            "Investment goal was not reached, money cannot be withdrawed"
        );
        owner.transfer(current_investment);
        emit ProjectFunded(current_investment);
    }

    function withdraw() public returns (bool) {
        require(now > mini_crowdfunding_expiry,
            "Crowdfunding still going on.");
        require(current_investment < investment_goal,
            "Crowdfunding goal reached. Withdrawing is impossible.");
        uint256 amount = balances[msg.sender];
        if (amount > 0) {
            // It is important to set this to zero because the recipient
            // can call this function again as part of the receiving call
            // before `send` returns.
            balances[msg.sender] = 0;

            if (!msg.sender.send(amount)) {
                // No need to call throw here, just reset the amount owing
                balances[msg.sender] = amount;
                return false;
            }
        }
        return true;
    }

}