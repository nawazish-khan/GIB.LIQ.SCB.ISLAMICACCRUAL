package com.psg.liq.gibislamicdailyacrrual;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.misys.liq.bm.accttran.AccountingOperation;
import com.misys.liq.bm.accttran.GLEntry;
import com.misys.liq.bm.desktopcore.main.cdt.accrue.AccrualCycle;
import com.misys.liq.bm.desktopcore.main.cdt.funding.Money;
import com.misys.liq.bm.desktopcore.main.cdt.share.LegalEntityShare;
import com.misys.liq.bm.desktopcore.main.cdt.share.OutstandingPortfolioShare;
import com.misys.liq.bm.desktopcore.main.cdt.share.PortfolioAccrualShare;
import com.misys.liq.core.batch.ScriptedBatchImplementation;
import com.misys.liq.core.js.interfaces.ScriptedBatchImplementationJS;
import com.misys.liq.infrastructure.LiqObject;
import com.misys.liq.infrastructure.LiqUtilities;
import com.misys.liq.infrastructure.bm.handlers.UnitOfWorkHandler;

public class IslamicDailyAccrualAction implements ScriptedBatchImplementationJS {

	@Override
	public Object execute(LiqObject liqObject, ScriptedBatchImplementation impl) {
		AccrualCycle acc = (AccrualCycle) liqObject;
		//acc.getShares().legalEntityShares();
		impl.logError("ACC RID: " + acc.getId() + " will process");
		// processing logic goes here
		impl.logError("ACC RID: " + acc.getId() + " did process");
		GLEntry glEntry = new GLEntry();
		if("5QF8WOOB".equals(acc.id))
		{
			List<LegalEntityShare> legalEntityShareList = acc.getShares().hostLegalEntityShares();
			for(LegalEntityShare share : legalEntityShareList)
			{

				List objOutstandingPortfolioShares = share.getPortfolioShares();
				for(Object objOutstandingPortfolioShare : objOutstandingPortfolioShares)
				{
					if(objOutstandingPortfolioShare instanceof PortfolioAccrualShare)
					{
						PortfolioAccrualShare ostPortfolioShare = (PortfolioAccrualShare) objOutstandingPortfolioShare;
						glEntry.setOwnerId(acc.getId());
						glEntry.setCurrency(acc.getCurrency());
						glEntry.setBranchCode(ostPortfolioShare.getBranchCode());
						glEntry.setBusinessTransactionCode("INTAC");
						glEntry.portfolioCode = ostPortfolioShare.getPortfolioCode();
						glEntry.amount = Money.clazz.fromAmount(new BigDecimal(2.000), "USD");
						glEntry.setDealId(acc.getOutstanding().getDeal().getId());
						glEntry.expenseCode = ostPortfolioShare.getExpenseCode();
						glEntry.setFacilityId(acc.getOutstanding().getFacility().getId());
						glEntry.glAccountNumber = "146156001";
						glEntry.glShortName = LiqUtilities.asGLShortName("INTRC");
						glEntry.setJournalId("1");
						glEntry.processingAreaCode = acc.getOutstanding().getProcessingAreaCode();
						glEntry.setServicingGroupId("HSF3VBKP");
						glEntry.ownerId = acc.id;
						glEntry.setOwnerType("APO");
						glEntry.entityCode = ostPortfolioShare.getBranchCode();
						glEntry.setSecurityId(acc.getOutstanding().getId());
						glEntry.setCustomerId(acc.getOutstanding().getBorrowerId());
						Set set = new HashSet();
						set.add("Loading");
						glEntry.setPrivateState(set);
						glEntry.setAccountingOperation(AccountingOperation.clazz.newDebit());
						set = new HashSet();
						set.add("forceSave");
						glEntry.setPrivateState(set);
						glEntry.dealTrackingNumber = acc.getDeal().getTrackingNumber();
						glEntry.description = "Test";
						glEntry.journalLine = "";
						glEntry.securityType="";
						glEntry.statusCode="PEND";
						glEntry.transactionBusinessDate = acc.getOutstanding().effectiveDate();
						glEntry.transactionEffectiveDate = acc.getOutstanding().effectiveDate();
						glEntry.valueDate = acc.getOutstanding().effectiveDate();
						glEntry.unitOfWorkHandler  = acc.unitOfWorkHandler;
						glEntry.save();


						glEntry.setOwnerId(acc.getId());
						glEntry.setCurrency(acc.getCurrency());
						glEntry.setBranchCode(ostPortfolioShare.getBranchCode());
						glEntry.setBusinessTransactionCode("INTAC");
						glEntry.portfolioCode = ostPortfolioShare.getPortfolioCode();
						glEntry.amount = Money.clazz.fromAmount(new BigDecimal(2.000), "USD");
						glEntry.setDealId(acc.getOutstanding().getDeal().getId());
						glEntry.expenseCode = ostPortfolioShare.getExpenseCode();
						glEntry.setFacilityId(acc.getOutstanding().getFacility().getId());
						glEntry.glAccountNumber = "146156001";
						glEntry.glShortName = LiqUtilities.asGLShortName("INTRC");
						glEntry.setJournalId("1");
						glEntry.processingAreaCode = acc.getOutstanding().getProcessingAreaCode();
						glEntry.setServicingGroupId("HSF3VBKP");
						glEntry.ownerId = acc.id;
						glEntry.setOwnerType("APO");
						glEntry.entityCode = ostPortfolioShare.getBranchCode();
						glEntry.setSecurityId(acc.getOutstanding().getId());
						glEntry.setCustomerId(acc.getOutstanding().getBorrowerId());
						Set setCr = new HashSet();
						setCr.add("Loading");
						glEntry.setPrivateState(setCr);
						glEntry.setAccountingOperation(AccountingOperation.clazz.newCredit());
						setCr = new HashSet();
						setCr.add("forceSave");
						glEntry.setPrivateState(setCr);
						glEntry.dealTrackingNumber = acc.getDeal().getTrackingNumber();
						glEntry.description = "Test";
						glEntry.journalLine = "";
						glEntry.securityType="";
						glEntry.statusCode="PEND";
						glEntry.transactionBusinessDate = acc.getOutstanding().effectiveDate();
						glEntry.transactionEffectiveDate = acc.getOutstanding().effectiveDate();
						glEntry.valueDate = acc.getOutstanding().effectiveDate();
						glEntry.unitOfWorkHandler  = acc.unitOfWorkHandler;
						glEntry.save();
					}
				}

			}

		}


		return acc;

	}

}
