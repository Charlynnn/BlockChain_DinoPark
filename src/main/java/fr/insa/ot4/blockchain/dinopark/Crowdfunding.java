package fr.insa.ot4.blockchain.dinopark;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class Crowdfunding extends Contract {
    private static final String BINARY = "6080604052603c60025560006003819055670de0b6b3a7640000600455600555600c805460ff1916905534801561003557600080fd5b50600080546001600160a01b031916331781556040805160608082018352838252670de0b6b3a7640000602080840182815260698587019081526008805460018181018355828b5297517ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee360039283028181019290925594517ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee48083019190915593517ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee59182015589518089018b529687526722b1c8c1227a0000878701818152606e898d019081528554808d018755868f5299519985028089019a909a55905189870155519782019790975589519788018a52958752620f4240948701948552607898870198895281548089018355918a52955195029182019490945590519281019290925592519201919091556002544201905561111e90819061019b90396000f3fe60806040526004361061007b5760003560e01c806367dd74ca1161004e57806367dd74ca1461011a57806367fe9b9c14610137578063c9e89b351461014c578063e8b5e51f146101615761007b565b806327e235e31461008057806331a3a506146100c55780633ccfd60b146100dc5780634376c4a614610105575b600080fd5b34801561008c57600080fd5b506100b3600480360360208110156100a357600080fd5b50356001600160a01b0316610169565b60408051918252519081900360200190f35b3480156100d157600080fd5b506100da61017b565b005b3480156100e857600080fd5b506100f161028a565b604080519115158252519081900360200190f35b34801561011157600080fd5b506100da61037f565b6100da6004803603602081101561013057600080fd5b5035610632565b34801561014357600080fd5b506100b3610877565b34801561015857600080fd5b506100f161087d565b6100da610ae7565b60096020526000908152604090205481565b6000546001600160a01b031633146101c45760405162461bcd60e51b8152600401808060200182810382526050815260200180610f496050913960600191505060405180910390fd5b6001544211610208576040805162461bcd60e51b815260206004820152601c60248201526000805160206110c9833981519152604482015290519081900360640190fd5b678ac7230489e800006003541061024457610221610c3b565b1561023a5760038054600281026006550260075561023f565b610288565b61027a565b60035460408051918252517f0a04d0fa584f2a6634f873c18ee22a5ece2ef1ed09fee44ce9f09391a2ac57cf9181900360200190a15b600c805460ff191660011790555b565b600060015442116102d0576040805162461bcd60e51b815260206004820152601c60248201526000805160206110c9833981519152604482015290519081900360640190fd5b678ac7230489e80000600354106103185760405162461bcd60e51b8152600401808060200182810382526035815260200180610de06035913960400191505060405180910390fd5b33600090815260096020526040902054801561037657336000818152600960205260408082208290555183156108fc0291849190818181858888f193505050506103765733600090815260096020526040812091909155905061037c565b60019150505b90565b3360008181526009602052604090205460015442116103d3576040805162461bcd60e51b815260206004820152601c60248201526000805160206110c9833981519152604482015290519081900360640190fd5b8061040f5760405162461bcd60e51b8152600401808060200182810382526051815260200180610d516051913960600191505060405180910390fd5b600c5460ff166104505760405162461bcd60e51b8152600401808060200182810382526050815260200180610e8a6050913960600191505060405180910390fd5b60065460055410156104935760405162461bcd60e51b815260040180806020018281038252603d815260200180610fd6603d913960400191505060405180910390fd5b6001600160a01b0382166000908152600b602052604090205460ff16156104eb5760405162461bcd60e51b8152600401808060200182810382526034815260200180610f156034913960400191505060405180910390fd5b60408051600180825281830190925260609160208083019080388339505060408051426020808301919091526bffffffffffffffffffffffff19606089901b16828401528251603481840301815260549092019092528051910120825192935091829150839060009061055a57fe5b6020908102919091018101919091526001600160a01b0385166000908152600b82526040808220805460ff19166001179055838252600a835280822080546001600160a01b031916339081179091558151818152808501849052606092810183815287519382019390935286517f05932f6f88ae95c8a3188ff5e1161a9739cb2e8e262d5ed067059ef532c77088959294938893909160808401918581019102808383895b838110156106175781810151838201526020016105ff565b5050505090500194505050505060405180910390a150505050565b60015442116106725760405162461bcd60e51b815260040180806020018281038252603e815260200180610da2603e913960400191505060405180910390fd5b600c5460ff166106b35760405162461bcd60e51b815260040180806020018281038252603d815260200180610f99603d913960400191505060405180910390fd5b678ac7230489e8000060035410156106fc5760405162461bcd60e51b815260040180806020018281038252606c81526020018061105d606c913960800191505060405180910390fd5b60045433903490830281146107425760405162461bcd60e51b815260040180806020018281038252604a815260200180611013604a913960600191505060405180910390fd5b6005805482019055604080518481526020808602820101909152606090848015610776578160200160208202803883390190505b50905060005b848110156107f55760408051426020808301919091523360601b828401528251603481840301815260549092019092528051910120825181908490849081106107c157fe5b6020908102919091018101919091526000918252600a90526040902080546001600160a01b0319163317905560010161077c565b507f05932f6f88ae95c8a3188ff5e1161a9739cb2e8e262d5ed067059ef532c7708883838360405180846001600160a01b03166001600160a01b031681526020018381526020018060200182810382528381815181526020019150805190602001906020028083836000838110156106175781810151838201526020016105ff565b60015481565b3360008181526009602052604081205460015491929142116108d4576040805162461bcd60e51b815260206004820152601c60248201526000805160206110c9833981519152604482015290519081900360640190fd5b600c5460ff166109155760405162461bcd60e51b8152600401808060200182810382526050815260200180610e8a6050913960600191505060405180910390fd5b60075460055410156109585760405162461bcd60e51b8152600401808060200182810382526030815260200180610e156030913960400191505060405180910390fd5b806109945760405162461bcd60e51b8152600401808060200182810382526045815260200180610e456045913960600191505060405180910390fd5b600080805b60085460001901811015610a2157600881815481106109b457fe5b906000526020600020906003020160000154841180156109f25750600881815481106109dc57fe5b9060005260206000209060030201600101548411155b15610a195760088181548110610a0457fe5b90600052602060002090600302016002015492505b600101610999565b50600880546000198101908110610a3457fe5b906000526020600020906003020160000154831115610a7457600880546000198101908110610a5f57fe5b90600052602060002090600302016002015491505b8115610adc5750604051606482840204906001600160a01b0385169082156108fc029083906000818181858888f1935050505015610acf575050506001600160a01b031660009081526009602052604081205550600161037c565b600094505050505061037c565b600094505050505090565b600154421115610b3e576040805162461bcd60e51b815260206004820152601b60248201527f43726f776466756e64696e6720616c726561647920656e6465642e0000000000604482015290519081900360640190fd5b60003411610b93576040805162461bcd60e51b815260206004820152601c60248201527f596f75206d75737420696e76657374206d6f7265207468616e20302e00000000604482015290519081900360640190fd5b60038054349081018255336000818152600960209081526040918290208054850190559354815183815294850152805191937f85177f287940f2f05425a4029951af0e047a7f9c4eaa9a6e6917bcd869f86695929081900390910190a1678ac7230489e8000060035410610c375760035460408051918252517ffbfd8ab7c24300fa9888cd721c8565a7da56759384781283684dcf7c7c4a846b9181900360200190a15b5050565b60006001544211610c93576040805162461bcd60e51b815260206004820152601d60248201527f43726f776466756e64696e67206861736e277420656e64656420796574000000604482015290519081900360640190fd5b678ac7230489e800006003541015610cdc5760405162461bcd60e51b815260040180806020018281038252603b815260200180610eda603b913960400191505060405180910390fd5b600080546003546040516001600160a01b039092169281156108fc029290818181858888f1935050505015610d485760035460408051918252517f2301e0a2ad8f6e28a7fbb09e96f5c14e588fac346ed5ce607e1a018bc76886469181900360200190a150600161037c565b50600061037c56fe596f752068617665206e6f7420696e76657374656420696e20746869732070726f6a6563742c20736f20796f752063616e277420636c61696d2061206672656520656e7472616e6365207469636b65742e43726f776466756e64696e67207374696c6c20676f696e67206f6e2c20706c656173652077616974206265666f726520627579696e67207469636b65742e43726f776466756e64696e6720676f616c20726561636865642e205769746864726177696e6720697320696d706f737369626c652e57652068617665206e6f74206561726e656420656e6f756768206d6f6e657920746f20706179206261636b207965742e596f752068617665206e6f7420696e76657374656420696e20746869732070726f6a656374206f7220616c72656164792068617665206265656e2070616964206261636b2e546865207061726b206973206e6f77206265696e67206275696c742e20506c65617365207761697420666f72206675727468657220616e6e6f756e63656d656e747320746f2074727920616761696e2e496e766573746d656e7420676f616c20776173206e6f7420726561636865642c206d6f6e65792063616e6e6f742062652077697468647261776564596f75206861766520616c726561647920726563656976656420796f7572206672656520656e7472616e6365207469636b65742e596f7520617265206e6f7420746865206f776e6572206f66207468652063726f776466756e64696e672c20796f752063616e6e6f742061636365737320746869732066756e6374696f6e616c6974792e576520617265207472656174696e672063726f776466756e64696e6720726573756c74732c20706c656173652074727920616761696e206c617465722e57652068617665206e6f74206561726e656420656e6f756768206d6f6e657920746f2067697665206177617920746865207469636b657473207965742e4572726f72203a20596f75206d7573742073656e642074686520636f72726563742070726963652c20796f75206861766520746f207061792031206574686572206279207469636b6574576520617265207665727920736f72727920746f20616e6e6f756e636520746861742074686520696e766573746d656e7420676f616c20686173206e6f74206265656e207265616368656420696e2074696d652c207468652070726f6a6563742069732061626f727465642e43726f776466756e64696e67207374696c6c20676f696e67206f6e2e00000000a26469706673582212208e4378633bb3124020a488406d8e43ffb5350bc7123ff5239a1b337c0b5cbfe764736f6c63430006020033";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_BUYTICKET = "buyTicket";

    public static final String FUNC_CLAIMPAYBACKWITHINTERESTS = "claimPayBackWithInterests";

    public static final String FUNC_CLAIMTICKETREWARD = "claimTicketReward";

    public static final String FUNC_CLOSEFUNDING = "closeFunding";

    public static final String FUNC_CROWDFUNDING_EXPIRY = "crowdfunding_expiry";

    public static final String FUNC_INVEST = "invest";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final Event BUYTICKET_EVENT = new Event("BuyTicket", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicArray<Bytes32>>() {}));
    ;

    public static final Event ETHRECEIVED_EVENT = new Event("EthReceived", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event EXPIREDDATE_EVENT = new Event("ExpiredDate", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event GOALREACHED_EVENT = new Event("GoalReached", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event PROJECTFUNDED_EVENT = new Event("ProjectFunded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event ABORTFUNDING_EVENT = new Event("abortFunding", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected Crowdfunding(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Crowdfunding(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<Crowdfunding> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Crowdfunding.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Crowdfunding> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Crowdfunding.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<BuyTicketEventResponse> getBuyTicketEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BUYTICKET_EVENT, transactionReceipt);
        ArrayList<BuyTicketEventResponse> responses = new ArrayList<BuyTicketEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BuyTicketEventResponse typedResponse = new BuyTicketEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.tickets_bought = (List<byte[]>) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BuyTicketEventResponse> buyTicketEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, BuyTicketEventResponse>() {
            @Override
            public BuyTicketEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BUYTICKET_EVENT, log);
                BuyTicketEventResponse typedResponse = new BuyTicketEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.tickets_bought = (List<byte[]>) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<BuyTicketEventResponse> buyTicketEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BUYTICKET_EVENT));
        return buyTicketEventObservable(filter);
    }

    public List<EthReceivedEventResponse> getEthReceivedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ETHRECEIVED_EVENT, transactionReceipt);
        ArrayList<EthReceivedEventResponse> responses = new ArrayList<EthReceivedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EthReceivedEventResponse typedResponse = new EthReceivedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<EthReceivedEventResponse> ethReceivedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, EthReceivedEventResponse>() {
            @Override
            public EthReceivedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ETHRECEIVED_EVENT, log);
                EthReceivedEventResponse typedResponse = new EthReceivedEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<EthReceivedEventResponse> ethReceivedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ETHRECEIVED_EVENT));
        return ethReceivedEventObservable(filter);
    }

    public List<ExpiredDateEventResponse> getExpiredDateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(EXPIREDDATE_EVENT, transactionReceipt);
        ArrayList<ExpiredDateEventResponse> responses = new ArrayList<ExpiredDateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ExpiredDateEventResponse typedResponse = new ExpiredDateEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ExpiredDateEventResponse> expiredDateEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ExpiredDateEventResponse>() {
            @Override
            public ExpiredDateEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(EXPIREDDATE_EVENT, log);
                ExpiredDateEventResponse typedResponse = new ExpiredDateEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Observable<ExpiredDateEventResponse> expiredDateEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(EXPIREDDATE_EVENT));
        return expiredDateEventObservable(filter);
    }

    public List<GoalReachedEventResponse> getGoalReachedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(GOALREACHED_EVENT, transactionReceipt);
        ArrayList<GoalReachedEventResponse> responses = new ArrayList<GoalReachedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            GoalReachedEventResponse typedResponse = new GoalReachedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.total_money_received = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<GoalReachedEventResponse> goalReachedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, GoalReachedEventResponse>() {
            @Override
            public GoalReachedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(GOALREACHED_EVENT, log);
                GoalReachedEventResponse typedResponse = new GoalReachedEventResponse();
                typedResponse.log = log;
                typedResponse.total_money_received = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<GoalReachedEventResponse> goalReachedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(GOALREACHED_EVENT));
        return goalReachedEventObservable(filter);
    }

    public List<ProjectFundedEventResponse> getProjectFundedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PROJECTFUNDED_EVENT, transactionReceipt);
        ArrayList<ProjectFundedEventResponse> responses = new ArrayList<ProjectFundedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProjectFundedEventResponse typedResponse = new ProjectFundedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.total_money_received = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ProjectFundedEventResponse> projectFundedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ProjectFundedEventResponse>() {
            @Override
            public ProjectFundedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PROJECTFUNDED_EVENT, log);
                ProjectFundedEventResponse typedResponse = new ProjectFundedEventResponse();
                typedResponse.log = log;
                typedResponse.total_money_received = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ProjectFundedEventResponse> projectFundedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROJECTFUNDED_EVENT));
        return projectFundedEventObservable(filter);
    }

    public List<AbortFundingEventResponse> getAbortFundingEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ABORTFUNDING_EVENT, transactionReceipt);
        ArrayList<AbortFundingEventResponse> responses = new ArrayList<AbortFundingEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AbortFundingEventResponse typedResponse = new AbortFundingEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AbortFundingEventResponse> abortFundingEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, AbortFundingEventResponse>() {
            @Override
            public AbortFundingEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ABORTFUNDING_EVENT, log);
                AbortFundingEventResponse typedResponse = new AbortFundingEventResponse();
                typedResponse.log = log;
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<AbortFundingEventResponse> abortFundingEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ABORTFUNDING_EVENT));
        return abortFundingEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> balances(String param0) {
        final Function function = new Function(
                FUNC_BALANCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> buyTicket(BigInteger number_ticket) {
        final Function function = new Function(
                FUNC_BUYTICKET, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(number_ticket)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> claimPayBackWithInterests() {
        final Function function = new Function(
                FUNC_CLAIMPAYBACKWITHINTERESTS, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> claimTicketReward() {
        final Function function = new Function(
                FUNC_CLAIMTICKETREWARD, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> closeFunding() {
        final Function function = new Function(
                FUNC_CLOSEFUNDING, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> crowdfunding_expiry() {
        final Function function = new Function(
                FUNC_CROWDFUNDING_EXPIRY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> invest() {
        final Function function = new Function(
                FUNC_INVEST, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> withdraw() {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static Crowdfunding load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Crowdfunding(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Crowdfunding load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Crowdfunding(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class BuyTicketEventResponse {
        public Log log;

        public String from;

        public BigInteger price;

        public List<byte[]> tickets_bought;
    }

    public static class EthReceivedEventResponse {
        public Log log;

        public String from;

        public BigInteger amount;
    }

    public static class ExpiredDateEventResponse {
        public Log log;
    }

    public static class GoalReachedEventResponse {
        public Log log;

        public BigInteger total_money_received;
    }

    public static class ProjectFundedEventResponse {
        public Log log;

        public BigInteger total_money_received;
    }

    public static class AbortFundingEventResponse {
        public Log log;

        public BigInteger amount;
    }
}
