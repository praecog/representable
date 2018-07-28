package hu.vr.representable;

public interface AnyTag {
	public static final class Domain implements AnyTag{
		private final String tagName;
		private Domain(String tagName){
			this.tagName = tagName;
		}
		public static final Domain tagName(String tagName) {
			return new Domain(tagName);
		}
		@Override
		public final String toString() {
			return tagName;
		}
		
	}
}
