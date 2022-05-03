package site.metacoding.blogv3.util;

public class Script {

    // 성공시 알림 없이 이동시켜주기 위한 메서드 오버로딩
    public static String href(String url) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("location.href='" + url + "';");
        sb.append("</script>");
        return sb.toString();
    }

    public static String href(String url, String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("location.href='" + url + "';");
        sb.append("</script>");
        return sb.toString();
    }

    public static String back(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("history.back();");
        sb.append("</script>");
        return sb.toString();
    }
}
