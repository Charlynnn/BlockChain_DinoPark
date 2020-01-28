pragma solidity ^0.6.1;



contract mortal {
    address owner;
    constructor() public { owner = msg.sender; }
}

contract crowdfunding is mortal {

    struct Investor {
    address id;
    uint256 investment;
    }

    struct Tier {
        int lower_bound;
        int upper_bound;
        int interest_rate;
    }

    uint public crowdfunding_expiry;
    uint256 constant investment_goal = 1000;
    uint256 current_investment = 0;


    Investor[] investors;
    Tier[] tiers = [Tier(1, 10, 105), Tier(11, 25, 110), Tier(1, 10, 120)];
    mapping (address => uint) public balances;

    event EthReceived(address from, uint256 amount);
    event GoalReached(uint256 total_money_received);
    event ExpiredDate();

    function invest()  public payable {
        require(
            now <= crowdfunding_expiry,
            "Crowdfunding already ended."
        );
        require(
            current_investment < investment_goal,
            "Goal already reached."
        );
        // check overflow of the balances
        address investor_address = msg.sender;
        uint256 eth_received = msg.value;

        current_investment += eth_received;
        Investor memory investor = Investor(investor_address, eth_received);
        investors.push(investor);
        balances[msg.sender] += eth_received;

        emit EthReceived(investor_address, current_investment);
        if(current_investment >= investment_goal)
            emit GoalReached(current_investment);
    }

    function endCrowfundingSuccess(){
        require(now > crowdfunding_expiry);
        require(current_investment >  investment_goal);

        owner.send(current_investment);
    }

    function withdraw() public returns (bool) {
        require(now > crowdfunding_expiry
                && current_investment < investment_goal,
                "Crowdfunding still going on.");

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


