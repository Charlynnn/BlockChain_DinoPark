package fr.insa.ot4.blockchain.dinopark;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
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
public class MiniCrowdfunding extends Contract {
    private static final String BINARY = "603c60059081556001608081815260a0839052610120604052601660e08181527f7375706572206578636c7573697665206669677572650000000000000000000061010090815260c091909152600c938455600d9490945590926200006891600e919062000478565b5050503480156200007857600080fd5b506040516200179138038062001791833981810160405260808110156200009e57600080fd5b8101908080516040519392919084640100000000821115620000bf57600080fd5b908301906020820185811115620000d557600080fd5b8251866020820283011164010000000082111715620000f357600080fd5b82525081516020918201928201910280838360005b838110156200012257818101518382015260200162000108565b50505050905001604052602001805160405193929190846401000000008211156200014c57600080fd5b9083019060208201858111156200016257600080fd5b82516401000000008111828201881017156200017d57600080fd5b82525081516020918201929091019080838360005b83811015620001ac57818101518382015260200162000192565b50505050905090810190601f168015620001da5780820380516001836020036101000a031916815260200191505b5060409081526020828101519290910151600080546001600160a01b03191633179055865192945092506200021591600191870190620004fd565b5082516200022b90600290602086019062000478565b50600382815560058290556040805160608101825260018082526019602080840191825284518086018652601681527f5665727920636f6f6c2044696e6f20737469636b65720000000000000000000081830152948401948552600b805493840181556000528351600080516020620017318339815191529390960292830195865590516000805160206200177183398151915283015592518051929493620002eb93600080516020620017518339815191529093019291019062000478565b505060408051606081018252601a8152601e602080830191825283518085018552601781527f6578636c75736976652044696e6f206b6579636861696e00000000000000000081830152938301938452600b80546001810182556000919091528351600390910260008051602062001731833981519152810191825592516000805160206200177183398151915284015593518051939550620003a593600080516020620017518339815191529093019291019062000478565b505060408051606081018252601f808252620186a06020808401918252845180860186529283527f5375706572206578636c75736976652044696e6f20636f6f6b696520626f780083820152938301918252600b80546001810182556000919091528351600390910260008051602062001731833981519152810191825591516000805160206200177183398151915283015591518051939550919362000462936000805160206200175183398151915290920192019062000478565b5050600554420160065550620005aa9350505050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620004bb57805160ff1916838001178555620004eb565b82800160010185558215620004eb579182015b82811115620004eb578251825591602001919060010190620004ce565b50620004f992915062000563565b5090565b82805482825590600052602060002090810192821562000555579160200282015b828111156200055557825182546001600160a01b0319166001600160a01b039091161782556020909201916001909101906200051e565b50620004f992915062000583565b6200058091905b80821115620004f957600081556001016200056a565b90565b6200058091905b80821115620004f95780546001600160a01b03191681556001016200058a565b61117780620005ba6000396000f3fe60806040526004361061009c5760003560e01c80633ccfd60b116100645780633ccfd60b1461013c57806362c9ea0b14610165578063bd701ef61461017a578063bf0c1f711461018f578063ca51cd8614610219578063e8b5e51f146102b45761009c565b806306c14271146100a157806322660016146100c857806327e235e3146100df57806331a3a506146101125780633467f85314610127575b600080fd5b3480156100ad57600080fd5b506100b66102bc565b60408051918252519081900360200190f35b3480156100d457600080fd5b506100dd6102c2565b005b3480156100eb57600080fd5b506100b66004803603602081101561010257600080fd5b50356001600160a01b031661062f565b34801561011e57600080fd5b506100dd610641565b34801561013357600080fd5b506100dd6106d5565b34801561014857600080fd5b50610151610969565b604080519115158252519081900360200190f35b34801561017157600080fd5b506100b6610a58565b34801561018657600080fd5b506100b6610a5e565b34801561019b57600080fd5b506101a4610a64565b6040805160208082528351818301528351919283929083019185019080838360005b838110156101de5781810151838201526020016101c6565b50505050905090810190601f16801561020b5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561022557600080fd5b5061022e610aef565b6040518084815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561027757818101518382015260200161025f565b50505050905090810190601f1680156102a45780820380516001836020036101000a031916815260200191505b5094505050505060405180910390f35b6100dd610b8a565b60065481565b6006544211610306576040805162461bcd60e51b815260206004820152601c60248201526000805160206110ff833981519152604482015290519081900360640190fd5b600354600454101561035f576040805162461bcd60e51b815260206004820152601f60248201527f5468652063726f776466756e64696e67207761732063616e63656c6c65642e00604482015290519081900360640190fd5b3360009081526009602052604090205460ff16156103c4576040805162461bcd60e51b815260206004820152601e60248201527f596f7520616c726561647920636c61696d656420796f75206d657263682e0000604482015290519081900360640190fd5b33600090815260076020526040902054806104105760405162461bcd60e51b815260040180806020018281038252602381526020018061111f6023913960400191505060405180910390fd5b606060005b600b546000190181101561052857600b818154811061043057fe5b9060005260206000209060030201600001548311801561046e5750600b818154811061045857fe5b9060005260206000209060030201600101548311155b1561052057600b818154811061048057fe5b600091825260209182902060026003909202018101805460408051601f6000196101006001861615020190931694909404918201859004850284018501905280835291929091908301828280156105185780601f106104ed57610100808354040283529160200191610518565b820191906000526020600020905b8154815290600101906020018083116104fb57829003601f168201915b505050505091505b600101610415565b50600b8054600019810190811061053b57fe5b90600052602060002090600302016000015482111561060657600b8054600019810190811061056657fe5b600091825260209182902060026003909202018101805460408051601f6000196101006001861615020190931694909404918201859004850284018501905280835291929091908301828280156105fe5780601f106105d3576101008083540402835291602001916105fe565b820191906000526020600020905b8154815290600101906020018083116105e157829003601f168201915b505050505090505b336000818152600960205260409020805460ff1916600117905561062b908290610d20565b5050565b60076020526000908152604090205481565b6006544211610685576040805162461bcd60e51b815260206004820152601c60248201526000805160206110ff833981519152604482015290519081900360640190fd5b6003546004541061069d57610698610e1c565b6106d3565b60045460408051918252517f0a04d0fa584f2a6634f873c18ee22a5ece2ef1ed09fee44ce9f09391a2ac57cf9181900360200190a15b565b6006544211610719576040805162461bcd60e51b815260206004820152601c60248201526000805160206110ff833981519152604482015290519081900360640190fd5b6003546004541015610772576040805162461bcd60e51b815260206004820152601d60248201527f5468652063726f7766756e64696e67207761732063616e63656c65642e000000604482015290519081900360640190fd5b336000908152600a602052604090205460ff16156107d7576040805162461bcd60e51b815260206004820181905260248201527f596f7520616c726561647920636c61696d65642074686973207265776172642e604482015290519081900360640190fd5b6000805b60015481101561082457336001600160a01b0316600182815481106107fc57fe5b6000918252602090912001546001600160a01b0316141561081c57600191505b6001016107db565b50806108615760405162461bcd60e51b815260040180806020018281038252603081526020018061100e6030913960400191505060405180910390fd5b33600090815260076020526040902054600c5481116108b15760405162461bcd60e51b81526004018080602001828103825260368152602001806110796036913960400191505060405180910390fd5b336000908152600a6020908152604091829020805460ff19166001908117909155600e80548451601f60026000199584161561010002959095019092169390930490810184900484028301840190945283825261062b93919290919083018282801561095e5780601f106109335761010080835404028352916020019161095e565b820191906000526020600020905b81548152906001019060200180831161094157829003601f168201915b505050505033610d20565b600060065442116109af576040805162461bcd60e51b815260206004820152601c60248201526000805160206110ff833981519152604482015290519081900360640190fd5b600354600454106109f15760405162461bcd60e51b8152600401808060200182810382526035815260200180610fd96035913960400191505060405180910390fd5b336000908152600760205260409020548015610a4f57336000818152600760205260408082208290555183156108fc0291849190818181858888f19350505050610a4f57336000908152600760205260408120919091559050610a55565b60019150505b90565b60045481565b60035481565b6002805460408051602060018416156101000260001901909316849004601f81018490048402820184019092528181529291830182828015610ae75780601f10610abc57610100808354040283529160200191610ae7565b820191906000526020600020905b815481529060010190602001808311610aca57829003601f168201915b505050505081565b600c8054600d54600e805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152949593949291830182828015610b805780601f10610b5557610100808354040283529160200191610b80565b820191906000526020600020905b815481529060010190602001808311610b6357829003601f168201915b5050505050905083565b6000546001600160a01b03163314610bd35760405162461bcd60e51b81526004018080602001828103825260508152602001806110af6050913960600191505060405180910390fd5b600654421115610c2a576040805162461bcd60e51b815260206004820152601b60248201527f43726f776466756e64696e6720616c726561647920656e6465642e0000000000604482015290519081900360640190fd5b60003411610c7f576040805162461bcd60e51b815260206004820152601d60248201527f796f75206d75737420696e76657374206d6f7265207468616e20302021000000604482015290519081900360640190fd5b60048054349081018255336000818152600760209081526040918290208054850190559354815183815294850152805191937f85177f287940f2f05425a4029951af0e047a7f9c4eaa9a6e6917bcd869f86695929081900390910190a16003546004541061062b5760045460408051918252517ffbfd8ab7c24300fa9888cd721c8565a7da56759384781283684dcf7c7c4a846b9181900360200190a15050565b60408051426020808301919091526bffffffffffffffffffffffff19606085901b16828401528251603481840301815260549092019092528051910120610d65610f28565b506040805180820190915283815260208082018390526008805460018101825560009190915282518051849360029093027ff3f7a9fe364faab93b216da50a3214154f22a0a2b415b23a84c8169e8b636ee30192610dc7928492910190610f40565b50602091820151600190910155604080516001600160a01b038616815291820184905280517f961999e836e2ba9b01cc0cf28a723eed5f55de84f132bbb7c83cffd24a9699a49281900390910190a150505050565b6006544211610e72576040805162461bcd60e51b815260206004820152601d60248201527f43726f776466756e64696e67206861736e277420656e64656420796574000000604482015290519081900360640190fd5b6003546004541015610eb55760405162461bcd60e51b815260040180806020018281038252603b81526020018061103e603b913960400191505060405180910390fd5b600080546004546040516001600160a01b039092169281156108fc029290818181858888f19350505050158015610ef0573d6000803e3d6000fd5b5060045460408051918252517f2301e0a2ad8f6e28a7fbb09e96f5c14e588fac346ed5ce607e1a018bc76886469181900360200190a1565b60408051808201909152606081526000602082015290565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610f8157805160ff1916838001178555610fae565b82800160010185558215610fae579182015b82811115610fae578251825591602001919060010190610f93565b50610fba929150610fbe565b5090565b610a5591905b80821115610fba5760008155600101610fc456fe43726f776466756e64696e6720676f616c20726561636865642e205769746864726177696e6720697320696d706f737369626c652e596f75206d7573742062652061206772616e6420706174726f6e20746f20636c61696d2074686973207265776172642e496e766573746d656e7420676f616c20776173206e6f7420726561636865642c206d6f6e65792063616e6e6f742062652077697468647261776564596f7520646964206e6f7420696e7665737420656e6f7567687420746f20636c61696d2074686520706174726f6e207265776172642e596f7520617265206e6f7420746865206f776e6572206f66207468652063726f776466756e64696e672c20796f752063616e6e6f742061636365737320746869732066756e6374696f6e616c6974792e43726f776466756e64696e67207374696c6c20676f696e67206f6e2e00000000596f7520646964206e6f7420696e7665737420696e20746869732070726f6a6563742ea2646970667358221220245661bb523c8b6d184807282d9913af482b43acd4719c65a6e33144d534288064736f6c634300060200330175b7a638427703f0dbe7bb9bbf987a2551717b34e79f33b5b1008d1fa01db90175b7a638427703f0dbe7bb9bbf987a2551717b34e79f33b5b1008d1fa01dbb0175b7a638427703f0dbe7bb9bbf987a2551717b34e79f33b5b1008d1fa01dba";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_CLAIMMERCH = "claimMerch";

    public static final String FUNC_CLAIMMERCHPATRON = "claimMerchPatron";

    public static final String FUNC_CLOSEFUNDING = "closeFunding";

    public static final String FUNC_CURRENT_INVESTMENT = "current_investment";

    public static final String FUNC_INVEST = "invest";

    public static final String FUNC_INVESTMENT_GOAL = "investment_goal";

    public static final String FUNC_MINI_CROWDFUNDING_EXPIRY = "mini_crowdfunding_expiry";

    public static final String FUNC_MINI_CROWDFUNDING_NAME = "mini_crowdfunding_name";

    public static final String FUNC_PATRONS_TIER = "patrons_tier";

    public static final String FUNC_WITHDRAW = "withdraw";

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

    public static final Event MERCHCLAIMED_EVENT = new Event("merchClaimed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
    ;

    protected MiniCrowdfunding(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MiniCrowdfunding(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static RemoteCall<MiniCrowdfunding> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, List<String> patrons, String name, BigInteger goal, BigInteger limit_time) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(patrons, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.generated.Uint256(goal), 
                new org.web3j.abi.datatypes.generated.Uint256(limit_time)));
        return deployRemoteCall(MiniCrowdfunding.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<MiniCrowdfunding> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, List<String> patrons, String name, BigInteger goal, BigInteger limit_time) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(patrons, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.generated.Uint256(goal), 
                new org.web3j.abi.datatypes.generated.Uint256(limit_time)));
        return deployRemoteCall(MiniCrowdfunding.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
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

    public List<MerchClaimedEventResponse> getMerchClaimedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MERCHCLAIMED_EVENT, transactionReceipt);
        ArrayList<MerchClaimedEventResponse> responses = new ArrayList<MerchClaimedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MerchClaimedEventResponse typedResponse = new MerchClaimedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.investor_address = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.merch_token = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<MerchClaimedEventResponse> merchClaimedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, MerchClaimedEventResponse>() {
            @Override
            public MerchClaimedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MERCHCLAIMED_EVENT, log);
                MerchClaimedEventResponse typedResponse = new MerchClaimedEventResponse();
                typedResponse.log = log;
                typedResponse.investor_address = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.merch_token = (byte[]) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<MerchClaimedEventResponse> merchClaimedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MERCHCLAIMED_EVENT));
        return merchClaimedEventObservable(filter);
    }

    public RemoteCall<TransactionReceipt> balances(String param0) {
        final Function function = new Function(
                FUNC_BALANCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> claimMerch() {
        final Function function = new Function(
                FUNC_CLAIMMERCH, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> claimMerchPatron() {
        final Function function = new Function(
                FUNC_CLAIMMERCHPATRON, 
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

    public RemoteCall<TransactionReceipt> current_investment() {
        final Function function = new Function(
                FUNC_CURRENT_INVESTMENT, 
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

    public RemoteCall<TransactionReceipt> investment_goal() {
        final Function function = new Function(
                FUNC_INVESTMENT_GOAL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> mini_crowdfunding_expiry() {
        final Function function = new Function(
                FUNC_MINI_CROWDFUNDING_EXPIRY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> mini_crowdfunding_name() {
        final Function function = new Function(
                FUNC_MINI_CROWDFUNDING_NAME, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> patrons_tier() {
        final Function function = new Function(
                FUNC_PATRONS_TIER, 
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

    public static MiniCrowdfunding load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MiniCrowdfunding(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static MiniCrowdfunding load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MiniCrowdfunding(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
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

    public static class MerchClaimedEventResponse {
        public Log log;

        public String investor_address;

        public byte[] merch_token;
    }
}
