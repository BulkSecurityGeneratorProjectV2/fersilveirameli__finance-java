package br.com.fsilveira.finance.utils.enums;

public enum Order {

	ASC {
		@Override
		public Order invert() {
			return DESC;
		}
	},
	DESC {
		@Override
		public Order invert() {
			return ASC;
		}
	};

	public abstract Order invert();

}
