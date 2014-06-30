// This file was auto-generated by the veyron vdl tool.
// Source(s):  bank.vdl
package com.veyron.examples.bank.vdlgen;

import com.veyron2.ipc.ServerCall;
import com.veyron2.ipc.VeyronException;

public class Server { 
	/* Server stub creation methods for interfaces in file: bank.vdl. */
	public static Object newBank(BankService service) { 
		return new BankStub(service);
	}
	public static Object newBankAccount(BankAccountService service) { 
		return new BankAccountStub(service);
	}
	
	/* Server stubs for interfaces in file: bank.vdl. */
	public static class BankStub {
		private final BankService service;

		BankStub(BankService service) {
			this.service = service;
		}
		/**
		 * Returns all tags associated with the provided method or null if the method isn't implemented
		 * by this service.
		 */
		@SuppressWarnings("unused")
		public Object[] getMethodTags(ServerCall call, String method) { 
			if (method == "Connect") {
				return new Object[]{ 2 };
			}
			return null;
		}
		// Methods from interface Bank.
		public BankService.ConnectOut connect(ServerCall call) throws VeyronException { 
			return this.service.connect(call);
		}
	}
	public static class BankAccountStub {
		private final BankAccountService service;

		BankAccountStub(BankAccountService service) {
			this.service = service;
		}
		/**
		 * Returns all tags associated with the provided method or null if the method isn't implemented
		 * by this service.
		 */
		@SuppressWarnings("unused")
		public Object[] getMethodTags(ServerCall call, String method) { 
			if (method == "Deposit") {
				return new Object[]{ 2 };
			}
			if (method == "Withdraw") {
				return new Object[]{ 2 };
			}
			if (method == "Transfer") {
				return new Object[]{ 2 };
			}
			if (method == "Balance") {
				return new Object[]{ 1 };
			}
			return null;
		}
		// Methods from interface BankAccount.
		public void deposit(ServerCall call, long amount) throws VeyronException { 
			this.service.deposit(call, amount);
		}
		public void withdraw(ServerCall call, long amount) throws VeyronException { 
			this.service.withdraw(call, amount);
		}
		public void transfer(ServerCall call, long receiver, long amount) throws VeyronException { 
			this.service.transfer(call, receiver, amount);
		}
		public long balance(ServerCall call) throws VeyronException { 
			return this.service.balance(call);
		}
	}
}
