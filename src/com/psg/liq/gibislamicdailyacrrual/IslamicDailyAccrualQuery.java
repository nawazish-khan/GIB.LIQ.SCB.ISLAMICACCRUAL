package com.psg.liq.gibislamicdailyacrrual;

import com.misys.liq.core.js.interfaces.batch.query.ScriptedBatchXQuery;

public class IslamicDailyAccrualQuery implements ScriptedBatchXQuery {

	@Override
	public String queryFunction(Object impl) {
		return "/AccrualCycle[LIQ.XQS.EQUAL(@createUserId,\"LS2BATCH\")]";
	}

	@Override
	public String businessObjectType() {
		return "ACC";
	}
}
