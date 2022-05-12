package com.topsun.listener;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

public class ExcelListener extends AnalysisEventListener {

	private List<Object> datas = new ArrayList<Object>();
	
	

	public List<Object> getDatas() {
		return datas;
	}

	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}

	//读取完之后的操作
	@Override
	public void doAfterAllAnalysed(AnalysisContext arg0) {
		
		
	}

	//每一条数据解析都会来调用
	@Override
	public void invoke(Object arg0, AnalysisContext arg1) {
		datas.add(arg0);
		
	}

}
