package fr.insa.ot4.blockchain.dinopark;

import java.util.Scanner;

/**
 * Demo program to deposit and call a smart contract. The used contract simply returns the received input string. Geth
 * must powered up and mining, first:
 geth --datadir="elephantchain" --rpcapi personal,db,eth,net,web3 --rpc --nodiscover --mine --minerthreads=4 console
 [*] runs the silent miner for
 * an uninvolved third account. [*] operates on the elephant chain
 */
public class Launcher {

    public static void main(String[] args) throws Exception {

        System.out.println("Trying to deposit a smart contract in the blockchain using java and web3j...");

        DinoParkDeployer deployer = new DinoParkDeployer();
        Crowdfunding crowndfunding = deployer.transferContract();

        System.out.println("Contract transmitted to blockchain -> Crowndfunding is alive and ready for interaction.");

        // Block program until enter key was hit.
        Scanner scanner = new Scanner(System.in);
        System.out.println("[Enter an investment to Crowndfunding]");
        scanner.nextLine();

        // Crowdfunding.ExpiredDate().watch({}, '', function(error, result) {
        //     if (!error) {
        //         console.log("oki ".");
        //         console.log("");
        //     }   
        // })
    }
}
