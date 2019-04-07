package com.idalko.plugins.jira.customfields;

import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import java.util.Stack;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Scanned
public class ParenBalanceCustomField extends AbstractSingleFieldType<String> {
	@ComponentImport CustomFieldValuePersister customFieldValuePersister;
	@ComponentImport GenericConfigManager genericConfigManager;
	public ParenBalanceCustomField(CustomFieldValuePersister customFieldValuePersister, GenericConfigManager genericConfigManager) {
    	super(customFieldValuePersister, genericConfigManager);
		this.customFieldValuePersister = customFieldValuePersister;
		this.genericConfigManager = genericConfigManager;
	}
	@Nonnull
	@Override
	protected PersistenceFieldType getDatabaseType() {
		return PersistenceFieldType.TYPE_LIMITED_TEXT;
	}
	@Nullable
	@Override
	protected Object getDbValueFromObject(String customFieldObject) {
		return getStringFromSingularObject(customFieldObject);
	}
	@Nullable
	@Override
	protected String getObjectFromDbValue(Object databaseValue) throws FieldValidationException {
		return getSingularObjectFromString((String) databaseValue);
	}
	@Override
	public String getStringFromSingularObject(String s) {
		return s == null ? null : s;
	}
	@Override 
	public String getSingularObjectFromString(String s) throws FieldValidationException {
		if (s != null) {
			if (this.isParenBalancedString(s)) {
				return s;
			} else {
				throw new FieldValidationException("Unbalanced parentheses");
			}
		} else {
			return null;
		}
	}
	public boolean isParenBalancedString(String s) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			Character prospectiveParen = s.charAt(i);
			if (prospectiveParen.equals('(')) {
				stack.push(prospectiveParen);
			} else {
				if (prospectiveParen.equals(')')) {
					if (!stack.empty()) {
						stack.pop();
					} else {
						return false;
					}
				}
			}
		}
		return stack.empty();
	}
}
