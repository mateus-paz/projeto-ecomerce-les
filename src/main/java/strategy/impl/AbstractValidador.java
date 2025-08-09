package strategy.impl;

import strategy.IStrategy;

public abstract class AbstractValidador implements IStrategy {
	protected StringBuilder sb = new StringBuilder();

	protected boolean isBlank(Object obj) {
		return obj == null || obj.toString().trim().equals("");
	}
}
