pragma solidity ^0.6.1;

struct investor {
    string id;
    int investment;
}

struct Tier {
    int inf_bound;
    int sup_bound;
    ufixed interest_rate; 
}

contract mortal {
    address owner;
    constructor() public { owner = msg.sender; }
}

contract crownfunding is mortal {
    uint counter;
    investor[] investors;
    Tier[] tiers;
    Tier t = Tier(1, 10, 1.05);
    ufixed[] interest_rates = [1.05,1.10,1.20];

    int constant investment_tier = 1000;


}
