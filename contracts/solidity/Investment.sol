pragma solidity ^0.6.1;

contract mortal {
    address owner;
    constructor() public { owner = msg.sender; }
}

contract investment is mortal {
    uint counter;
}
