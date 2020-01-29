pragma solidity ^0.6.1;

contract mortal {
    address payable owner;
    constructor() public { owner = msg.sender; }
}

contract crowdfunding is mortal {

    struct Investor {
        address payable id;
        uint256 investment;
    }

    struct Client {
        address payable id;
        uint256 money;
    }

    struct Tier {
        int lower_bound;
        int upper_bound;
        int interest_rate;
    }

    uint public crowdfunding_expiry;
    uint crowdfunding_duration = 5 weeks; //can be changed to whatever for testing purposes (units : seconds, minutes, hours....)
    uint256 constant investment_goal = 10 ether; //this is wei. To get ether, type "1000 ether", same goes for wei, finney, szabo
    uint256 current_investment = 0;
    uint256 ticket_price = 1 ether;


    Investor[] investors;
    Client[] clients;
    Tier[] tiers;
    mapping (address => uint) public balances;
    
    event EthReceived(address payable from, uint256 amount);
    event BuyTicket(address payable from, uint256 price);
    event GoalReached(uint256 total_money_received);
    event ExpiredDate();
    event abortFunding(uint256 amount);

    constructor() public {
        tiers.push(Tier(1, 10, 105));
        tiers.push(Tier(11, 25, 110));
        tiers.push(Tier(26, 1000000, 120)); //only use the lower bound for the top tier

        crowdfunding_expiry = now + crowdfunding_duration;
    }

    function invest()  public payable {
        //TODO implement time limite
        require(
            now <= crowdfunding_expiry,
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
        Investor memory investor = Investor(investor_address, eth_received);
        investors.push(investor);
        balances[msg.sender] += eth_received;

        emit EthReceived(investor_address, current_investment);
        if(current_investment >= investment_goal)
            emit GoalReached(current_investment);
    }

    function commitFunding() public {
        //require(now > crowdfunding_expiry);
        require(current_investment >  investment_goal);

        owner.transfer(current_investment);
    }

    function withdraw() public returns (bool) {
        require(now > crowdfunding_expiry,
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

    function buyTicket(uint ticket)  public payable {
        require(
            current_investment >= investment_goal,
            "The project is not completely crowdfunding yet, please wait before buying ticket"
        );

        address payable client_address = msg.sender;
        uint256 eth_ticket = msg.value;

        require(
            eth_ticket == ticket*ticket_price,
            "Error : You must send the correct price, you have to pay 1 ether by ticket");

        current_investment += eth_ticket;
        Client memory client = Client(client_address, eth_ticket);
        clients.push(client);

        emit BuyTicket(client_address, eth_ticket);
        if(current_investment >= investment_goal)
            emit GoalReached(current_investment);
    }
    
    function closeFunding() public{
        require(now > crowdfunding_expiry,
                "Crowdfunding still going on.");
        if(current_investment >=  investment_goal)
            commitFunding();
        else
            emit abortFunding(current_investment);
    }
}


