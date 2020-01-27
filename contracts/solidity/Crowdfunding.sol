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
    Tier[] tiers;
    Tier t = Tier(1, 10, 105);

    int constant investment_tier = 1000;

    function invest() public pure returns (int) {
        return 1;
    }
}
