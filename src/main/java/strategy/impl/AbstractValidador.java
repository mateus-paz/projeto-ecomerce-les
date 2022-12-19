package strategy.impl;

import strategy.IStrategy;

public abstract class AbstractValidador implements IStrategy{
	protected StringBuilder sb = new StringBuilder();

	protected boolean isNull(Object obj){
		if(obj == null || obj.toString().trim() == "")
			return true;
		return false;
	}
}
