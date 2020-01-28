pragma solidity ^0.6.1;

struct investor {
    string id;
    int investment;
}

struct Tier {
    int inf_bound;
    int sup_bound;
    int interest_rate;
}

contract mortal {
    address owner;
    constructor() public { owner = msg.sender; }
}

contract crowdfunding is mortal {
    uint counter;
    investor[] investors;
    Tier[] tiers = [Tier(1, 10, 105), Tier(11, 25, 110), Tier(1, 10, 120)];
    int constant investment_goal = 1000;
    int current_investment = 0;

    function invest(int money) public pure returns (bool){

        return true;
    }
}
