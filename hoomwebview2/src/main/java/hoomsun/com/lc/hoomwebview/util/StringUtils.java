//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package hoomsun.com.lc.hoomwebview.util;

public class StringUtils {
    public StringUtils() {
    }

    public static boolean isEmpty(String var0) {
        return var0 == null || var0.length() <= 0;
    }

    public static String convertObjectToString(Object var0) {
        return var0 != null ? (var0 instanceof String ? ((String) var0).toString() : (var0 instanceof Integer ? "" + ((Integer) var0).intValue() : (var0 instanceof Long ? "" + ((Long) var0).longValue() : (var0 instanceof Double ? "" + ((Double) var0).doubleValue() : (var0 instanceof Float ? "" + ((Float) var0).floatValue() : (var0 instanceof Short ? "" + ((Short) var0).shortValue() : (var0 instanceof Byte ? "" + ((Byte) var0).byteValue() : (var0 instanceof Boolean ? ((Boolean) var0).toString() : (var0 instanceof Character ? ((Character) var0).toString() : var0.toString()))))))))) : "";
    }

    public static int hashCode(String var0) {
        int var1 = 0;
        if (var1 == 0 && var0.length() > 0) {
            char[] var2 = var0.toCharArray();

            for (int var3 = 0; var3 < var2.length; ++var3) {
                var1 = 31 * var1 + var2[var3];
            }
        }

        return var1;
    }


    public static String clearSpace(String string) {
        if (string == null) {
            return string;
        } else
            return string.replaceAll(" ", "");
    }

}
