package fr.insa.ot4.blockchain.dinopark;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
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
    private static final String BINARY = "6080604052600060025534801561001557600080fd5b50600080546001600160a01b031916331790556104d1806100376000396000f3fe60806040526004361061004a5760003560e01c806327e235e31461004f5780633ccfd60b1461009457806367fe9b9c146100bd578063760b588e146100d2578063e8b5e51f146100e9575b600080fd5b34801561005b57600080fd5b506100826004803603602081101561007257600080fd5b50356001600160a01b03166100f1565b60408051918252519081900360200190f35b3480156100a057600080fd5b506100a9610103565b604080519115158252519081900360200190f35b3480156100c957600080fd5b50610082610204565b3480156100de57600080fd5b506100e761020a565b005b6100e7610266565b60046020526000908152604090205481565b6000600154421161015b576040805162461bcd60e51b815260206004820152601c60248201527f43726f776466756e64696e67207374696c6c20676f696e67206f6e2e00000000604482015290519081900360640190fd5b6103e86002541061019d5760405162461bcd60e51b81526004018080602001828103825260358152602001806104676035913960400191505060405180910390fd5b3360009081526004602052604090205480156101fb57336000818152600460205260408082208290555183156108fc0291849190818181858888f193505050506101fb57336000908152600460205260408120919091559050610201565b60019150505b90565b60015481565b600154421161021857600080fd5b6103e86002541161022857600080fd5b600080546002546040516001600160a01b039092169281156108fc029290818181858888f19350505050158015610263573d6000803e3d6000fd5b50565b6001544211156102bd576040805162461bcd60e51b815260206004820152601b60248201527f43726f776466756e64696e6720616c726561647920656e6465642e0000000000604482015290519081900360640190fd5b6103e86002541061030d576040805162461bcd60e51b815260206004820152601560248201527423b7b0b61030b63932b0b23c903932b0b1b432b21760591b604482015290519081900360640190fd5b6002805434908101909155339061032261044f565b506040805180820182526001600160a01b03848116808352602080840186815260038054600181018255600091825286517fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85b600292830290810180546001600160a01b031916929098169190911790965591517fc2575a0e9e593c00f959f8c92f12db2869c3395a3b0502d05e2516446f71f85c9095019490945533845260048252928590208054870190559154845191825291810191909152825191927f85177f287940f2f05425a4029951af0e047a7f9c4eaa9a6e6917bcd869f8669592918290030190a16103e86002541061044a5760025460408051918252517ffbfd8ab7c24300fa9888cd721c8565a7da56759384781283684dcf7c7c4a846b9181900360200190a15b505050565b60408051808201909152600080825260208201529056fe43726f776466756e64696e6720676f616c20726561636865642e205769746864726177696e6720697320696d706f737369626c652ea26469706673582212200a420ccabf52e910d576ec0b212d13f752055245b485b720f8224db09a4c3d8964736f6c63430006010033";

    public static final String FUNC_BALANCES = "balances";

    public static final String FUNC_CROWDFUNDING_EXPIRY = "crowdfunding_expiry";

    public static final String FUNC_ENDCROWFUNDINGSUCCESS = "endCrowfundingSuccess";

    public static final String FUNC_INVEST = "invest";

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

    public RemoteCall<TransactionReceipt> balances(String param0) {
        final Function function = new Function(
                FUNC_BALANCES, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(param0)), 
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

    public RemoteCall<TransactionReceipt> endCrowfundingSuccess() {
        final Function function = new Function(
                FUNC_ENDCROWFUNDINGSUCCESS, 
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
}
