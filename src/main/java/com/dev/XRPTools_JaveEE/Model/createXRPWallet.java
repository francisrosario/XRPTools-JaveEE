package com.dev.XRPTools_JaveEE.Model;

import okhttp3.HttpUrl;
import org.xrpl.xrpl4j.client.JsonRpcClientErrorException;
import org.xrpl.xrpl4j.client.XrplClient;
import org.xrpl.xrpl4j.client.faucet.FaucetClient;
import org.xrpl.xrpl4j.client.faucet.FundAccountRequest;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoRequestParams;
import org.xrpl.xrpl4j.model.client.accounts.AccountInfoResult;
import org.xrpl.xrpl4j.model.transactions.Address;
import org.xrpl.xrpl4j.model.transactions.XAddress;
import org.xrpl.xrpl4j.wallet.DefaultWalletFactory;
import org.xrpl.xrpl4j.wallet.Wallet;
import org.xrpl.xrpl4j.wallet.WalletFactory;

public class createXRPWallet {

    private String walletAddress;
    public String getWalletAddress() {
        return walletAddress;
    }

    public createXRPWallet(){

    }
    public void createXRPWalletX() throws JsonRpcClientErrorException {

        System.out.println("Running the GetAccountInfo sample...");
        // Construct a network client
        final HttpUrl rippledUrl = HttpUrl.get("https://s.altnet.rippletest.net:51234/");
        System.out.println("Constructing an XrplClient connected to " + rippledUrl);
        XrplClient xrplClient = new XrplClient(rippledUrl);

        // Create a Wallet using a WalletFactory
        WalletFactory walletFactory = DefaultWalletFactory.getInstance();
        final Wallet testWallet = walletFactory.randomWallet(true).wallet();
        System.out.println("Generated a wallet with the following public key: " + testWallet.publicKey());

        // Get the Classic and X-Addresses from testWallet
        final Address classicAddress = testWallet.classicAddress();
        final XAddress xAddress = testWallet.xAddress();

        createXRPWallet obj = new createXRPWallet();
        StringBuilder sb = new StringBuilder();
        sb.append("X-ADDRESS: ").append(xAddress).append(" CLASSIC ADDRESS: ").append(classicAddress).append(" PUBLIC KEY : ").append(testWallet.publicKey());
        walletAddress = sb.toString();
    }


}
