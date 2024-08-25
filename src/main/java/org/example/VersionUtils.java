package org.example;

/**
 * @author zhangyouhao @ Epoch Inc.
 * @since 2022/5/16
 */
//public class VersionUtils {
//
//    /**
//     * <code>
//     * if (version1 < version2)
//     * return -1
//     * else if (version1 > version2)
//     * return 1
//     * else
//     * return 0
//     * </code>
//     */
//    public static int compare( String version1,  String version2, boolean majorOnly) {
////        if (TextUtils.isEmpty(version1) && TextUtils.isEmpty(version2)) {
////            return 0;
////        } else if (TextUtils.isEmpty(version1) && !TextUtils.isEmpty(version2)) {
////            return -1;
////        } else if (!TextUtils.isEmpty(version1) && TextUtils.isEmpty(version2)) {
////            return 1;
////        }
//
//        String vlReal = version1.replaceAll("-.*", "");
//        String vrReal = version2.replaceAll("-.*", "");
//        return compare(vlReal.split("\\."), vrReal.split("\\."), majorOnly);
//    }
//
//    private static int compare(String[] vl, String[] vr, boolean majorOnly) {
//        int common = Math.min(vl.length, vr.length);
//        if (majorOnly) {
//            // major只比较前2位
//            common = Math.min(common, 2);
//        }
//        for (int i = 0; i < common; i++) {
//            int l = Integer.parseInt(vl[i]);
//            int r = Integer.parseInt(vr[i]);
//            if (l < r) {
//                return -1;
//            } else if (l > r) {
//                return 1;
//            }
//        }
//        if (vl.length < vr.length) {
//            return -1;
//        } else if (vl.length > vr.length) {
//            return 1;
//        }
//
//        return 0;
//    }
//
//    /**
//     * 版本号比较
//     *
//     * @param version1
//     * @param version2
//     * @return
//     */
//    public static int compareVersion(String version1, String version2) {
//        if (version1.equals(version2)) {
//            return 0;
//        }
//        String[] version1Array = version1.split("\\.");
//        String[] version2Array = version2.split("\\.");
//        int index = 0;
//        // 获取最小长度值
//        int minLen = Math.min(version1Array.length, version2Array.length);
//        int diff = 0;
//        // 循环判断每位的大小
//        while (index < minLen
//                && (diff = Integer.parseInt(version1Array[index])
//                - Integer.parseInt(version2Array[index])) == 0) {
//            index++;
//        }
//        if (diff == 0) {
//            // 如果位数不一致，比较多余位数
//            for (int i = index; i < version1Array.length; i++) {
//                if (Integer.parseInt(version1Array[i]) > 0) {
//                    return 1;
//                }
//            }
//
//            for (int i = index; i < version2Array.length; i++) {
//                if (Integer.parseInt(version2Array[i]) > 0) {
//                    return -1;
//                }
//            }
//            return 0;
//        } else {
//            return diff > 0 ? 1 : -1;
//        }
//    }
//}


public class VersionUtils {

    /**
     * <code>
     * if (vl < vr)
     * return -1
     * else if (vl > vr)
     * return 1
     * else
     * return 0
     * </code>
     *
     * @param vl
     * @param vr
     * @return
     */
    public static int compare(String vl, String vr) {
        return compare(vl.split("\\."), vr.split("\\."));
    }

    private static int compare(String[] vl, String[] vr) {
        int common = Math.min(vl.length, vr.length);
        for (int i = 0; i < common; i++) {
            int r = cmpSingle(vl[i], vr[i]);
            if (r != 0) {
                return r;
            }
        }
        if (vl.length < vr.length) {
            return -1;
        } else if (vl.length > vr.length) {
            return 1;
        }

        return 0;
    }

    protected static int cmpSingle(String c1, String c2) {
        String[] parts1 = c1.split("-");
        String[] parts2 = c2.split("-");
        int l = Integer.parseInt(parts1[0]);
        int r = Integer.parseInt(parts2[0]);
        if (l < r) {
            return -1;
        } else if (l > r) {
            return 1;
        }
        if (parts1.length < 2 && parts2.length < 2) {
            return 0;
        }
        if (parts1.length < parts2.length) {
            return 1;
        } else if (parts1.length > parts2.length) {
            return -1;
        } else {
            return parts1[1].compareTo(parts2[1]);
        }
    }

    /**
     * Check if the current version is in the specified version range.
     *
     * @param min
     * @param max
     * @param version
     */
    public static boolean contains(String min, String max, String version) {
        if (compare(min, version) <= 0 && compare(max, version) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * @param version : match all for null or ""
     * @param min     : match all for null or ""
     * @param max     : match all for null or ""
     * @return
     */
    public static boolean containsAllowBlank(String min, String max, String version) {
        if (compare(version, min) < 0) {
            return false;
        }
        if (compare(version, max) > 0) {
            return false;
        }
        return true;
    }

    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("\\.");
        String[] version2Array = version2.split("\\.");
        int index = 0;
        // 获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        // 循环判断每位的大小
        while (index < minLen
                && (diff = Integer.parseInt(version1Array[index])
                - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            // 如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

}