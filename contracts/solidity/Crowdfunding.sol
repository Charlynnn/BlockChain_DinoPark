pragma solidity ^0.6.1;

contract mortal {
    address payable owner;
    constructor() public { owner = msg.sender; }
}

contract crowdfunding is mortal {

    struct Client {
        address payable id;
        uint256 money;
    }

    struct Ticket {
        uint256 price;
        string token;
    }

    struct Tier {
        uint256 lower_bound;
        uint256 upper_bound;
        uint256 interest_rate;
    }

    uint public crowdfunding_expiry;
    uint crowdfunding_duration = 1 minutes; //can be changed to whatever for testing purposes (units : seconds, minutes, hours....)
    uint256 constant investment_goal = 10 ether; //this is wei. To get ether, type "1000 ether", same goes for wei, finney, szabo
    uint256 current_investment = 0;
    uint256 ticket_price = 1 ether;
    uint256 current_earnings = 0;
    uint256 earnings_goal1;
    uint256 earnings_goal2 = 10;

    address payable[] investors;
    Client[] clients;
    Tier[] tiers;
    
    mapping (address => uint) public balances;
    mapping (address => Ticket) tickets;
    
    event EthReceived(address payable from, uint256 amount);
    event BuyTicket(address payable from, uint256 price);
    event ProjectFunded(uint256 total_money_received);
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
        investors.push(investor_address);
        balances[msg.sender] += eth_received;

        emit EthReceived(investor_address, current_investment);
        if(current_investment >= investment_goal)
            emit GoalReached(current_investment);
    }

    function commitFunding() public {
        /*Test :
        Fails on goal not reached : OK
        Fails on expiry date not reached : OK
        Send correct amount of money : OK
        Always send to owner : OK
        */
        require(
            now > crowdfunding_expiry,
            "Crowdfunding hasn't ended yet"
        );
        require(
            current_investment >=  investment_goal,
            "Investment goal was not reached, money cannot be withdrawed"
        );
        owner.send(current_investment);
        emit ProjectFunded(current_investment);
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

    function buyTicket(uint number_ticket)  public payable {
        require(
            current_investment >= investment_goal,
            "The project is not completely crowdfunding yet, please wait before buying ticket"
        );

        address payable client_address = msg.sender;
        uint256 eth_ticket = msg.value;

        require(
            eth_ticket == number_ticket*ticket_price,
            "Error : You must send the correct price, you have to pay 1 ether by ticket");

        current_earnings += eth_ticket;
        Client memory client = Client(client_address, eth_ticket);
        clients.push(client);

        Ticket memory ticket = Ticket(eth_ticket, "testToken");
        tickets[client_address] = ticket;

        //if(!client_address.call(bytes4(bytes32(sha3("buyTicket(address,uint256,address,bytes)"))), client_address, _value, this, _extraData)) { throw; }
        //return true;

        emit BuyTicket(client_address, eth_ticket);
    }

    function closeFunding() public{
        require(now > crowdfunding_expiry,
                "Crowdfunding still going on.");
        if(current_investment >=  investment_goal)
            commitFunding();
        else
            emit abortFunding(current_investment);
    }
    
    function claimPayBackWithInterests() public {
        require(now > crowdfunding_expiry,
               "Crowdfunding still going on.");
        require(earnings > earnings_goal2,
                "We have not earned enough money to pay back yet.");
        address payable investor_address = msg.sender;
        //uint256 eth_received = msg.value;
        uint256 money_invested = balances[investor_address];
        for (uint i=0; i<3; i++) {
            if(money_invested > tiers[i].lower_bound && money_invested <= tiers[i].upper_bound){
                uint256 pb = money_invested*tiers[i].interest_rate;
            }
                
        }
    }
}


