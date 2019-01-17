package kr.brian.study.common.extension;

public interface ExtensionManagerOperation {

    /**
     * Call a method on the handler using some params. This generally involves casting to the proper types. For example:
     * </p>
     * <pre>
     * {@code
     *  public static final ExtensionManagerOperation applyAdditionalFilters = new ExtensionManagerOperation() {
     *        @Override
     *        public ExtensionResultStatusType execute(ExtensionHandler handler, Object... params) {
     *            return ((OfferServiceExtensionHandler) handler).applyAdditionalFilters((List<Offer>) params[0], (Order) params[1]);
     *        }
     *  };
     * }
     * </pre>
     * @param handler
     * @param params
     * @return the result
     */
    ExtensionResultStatusType execute(ExtensionHandler handler, Object... params);

}
