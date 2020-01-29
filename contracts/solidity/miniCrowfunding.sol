pragma solidity ^0.6.1;

contract miniCrowdfunding{

    struct Tier {
        int lower_bound;
        uint256 upper_bound;
        uint256 interest_rate;
        string merch_reward;
    }


    address[] grand_patrons;
    string public mini_crowdfunding_name;
    uint256 public goal_investment;
    uint256 public current_investment;
    uint public mini_crowdfunding_expiry;

    Tier[] public tiers;
    Tier public  patrons_tier = Tier(5, 1, 0, "exclusive figure");

    constructor(address[] memory patrons, string memory name, uint expiry, uint256 goal) public{
        grand_patrons = patrons;
        mini_crowdfunding_name = name;
        goal_investment = goal;
        mini_crowdfunding_expiry = now + expiry;
        tiers.push(Tier(1, 10, 105,"no merch"));
        tiers.push(Tier(11, 25, 110,"no merch"));
        tiers.push(Tier(26, 1000000, 120, "exclusive keychain"));
    }
}