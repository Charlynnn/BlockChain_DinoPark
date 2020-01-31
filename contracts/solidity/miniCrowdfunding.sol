pragma solidity ^0.6.1;

contract mortal {
    address payable owner;
    constructor() public { owner = msg.sender; }
}


contract miniCrowdfunding is mortal{

    struct Tier {
        uint256 lower_bound;
        uint256 upper_bound;
        string merch_reward;
    }
    struct Merch {
        string name;
        bytes32 token;
    }

    address[] grand_patrons;
    string public mini_crowdfunding_name;
    uint256 public investment_goal;
    uint256 public current_investment;
    uint mini_crowdfunding_duration =  1 minutes;
    uint public mini_crowdfunding_expiry;

    mapping (address => uint) public balances;
    Merch[] claimed_merch;

    mapping(address => bool) has_claimed_merch;
    mapping(address => bool) has_claimed_patron_merch;

    Tier[] private tiers;
    Tier public  patrons_tier = Tier(1, 5, "super exclusive figure");

    //Events

    event EthReceived(address payable from, uint256 amount);
    event ProjectFunded(uint256 total_money_received);
    event GoalReached(uint256 total_money_received);
    event ExpiredDate();
    event abortFunding(uint256 amount);
    event merchClaimed(address investor_address, bytes32 merch_token);


    constructor(address[] memory patrons, string memory name, uint256 goal) public{
        grand_patrons = patrons;
        mini_crowdfunding_name = name;
        investment_goal = goal;
        tiers.push(Tier(1, 25,"Very cool Dino sticker"));
        tiers.push(Tier(26, 30, "exclusive Dino keychain"));
        tiers.push(Tier(31, 100000, "Super exclusive Dino cookie box"));

        mini_crowdfunding_expiry = now + mini_crowdfunding_duration;
    }

    function invest()  public payable {
        require(msg.sender == owner,
            "You are not the owner of the crowdfunding, you cannot access this functionality.");
        require(
            now <= mini_crowdfunding_expiry,
            "Crowdfunding already ended."
        );
        require(
            msg.value > 0,
            "you must invest more than 0 !"
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

    function commitFunding() private{
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


    function claimMerch() public {
        require(now > mini_crowdfunding_expiry,
            "Crowdfunding still going on.");
        require(current_investment >= investment_goal,
            "The crowdfunding was cancelled.");
        require(
            !has_claimed_merch[msg.sender],
            "You already claimed you merch."
        );
        uint256 money_invested = balances[msg.sender];

        require(
            money_invested > 0,
            "You did not invest in this project."
        );
        string memory obtained_merch;
        //For all investors
        for (uint i=0; i<(tiers.length - 1); i++) {
            if(money_invested > tiers[i].lower_bound && money_invested <= tiers[i].upper_bound){
                obtained_merch = tiers[i].merch_reward;
            }
        }
        if(money_invested > tiers[tiers.length - 1].lower_bound)
            obtained_merch = tiers[tiers.length - 1].merch_reward;

        has_claimed_merch[msg.sender] = true;
        sendMerchToken(obtained_merch, msg.sender);
    }

    function claimMerchPatron() public {
        require(now > mini_crowdfunding_expiry,
            "Crowdfunding still going on.");
        require(current_investment >= investment_goal,
            "The crowfunding was canceled.");
        require(
            !has_claimed_patron_merch[msg.sender],
            "You already claimed this reward."
        );
        //Check if sender is grand patron
        bool is_patron = false;
        for(uint256 i = 0; i<grand_patrons.length; i++){
            if(grand_patrons[i] == msg.sender){
                is_patron = true;
            }
        }
        require(is_patron, "You must be a grand patron to claim this reward.");

        //Check if patron has invested enought to claim reward
        uint256 investmnent = balances[msg.sender];
        require(
            investmnent > patrons_tier.lower_bound,
            "You did not invest enought to claim the patron reward."
        );
        //Set has_claimed_patron_merch to true
        has_claimed_patron_merch[msg.sender] = true;
        sendMerchToken(patrons_tier.merch_reward, msg.sender);
    }

    function sendMerchToken(string memory obtained_merch, address investor_address) private {
        bytes32 merch_token = keccak256(abi.encodePacked(now, investor_address));
        Merch memory merch = Merch(obtained_merch, merch_token);
        claimed_merch.push(merch);
        emit merchClaimed(investor_address, merch_token);
    }


    function closeFunding() public{
        require(now > mini_crowdfunding_expiry,
            "Crowdfunding still going on.");
        if(current_investment >=  investment_goal){
            commitFunding();
        }
        else
            emit abortFunding(current_investment);
    }

}
