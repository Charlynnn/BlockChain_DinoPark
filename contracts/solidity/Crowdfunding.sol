pragma solidity ^0.6.1;

struct investor {
    string id;
    int investment;
}

contract mortal {
    address owner;
    constructor() public { owner = msg.sender; }
}

contract crownfunding is mortal {
    uint counter;
    investor[] investors;
}
