package fr.insa.ot4.blockchain.dinopark;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

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
    private static final String BINARY = "60e060405260016080819052600a60a0819052606960c0819052600492909255600555600655600080546001600160a01b031916331790556080806100456000396000f3fe6080604052348015600f57600080fd5b506004361060285760003560e01c8063e8b5e51f14602d575b600080fd5b60336045565b60408051918252519081900360200190f35b60019056fea2646970667358221220fc0208e3e28b811c78920b3e9e59ea3a102356b8bf7e071e5acae4a6c325b78664736f6c63430006010033";

    public static final String FUNC_INVEST = "invest";

    protected Crowdfunding(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Crowdfunding(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> invest() {
        final Function function = new Function(
                FUNC_INVEST, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Crowdfunding> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Crowdfunding.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Crowdfunding> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Crowdfunding.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Crowdfunding load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Crowdfunding(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Crowdfunding load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Crowdfunding(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
