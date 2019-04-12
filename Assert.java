public class Assert {
    public static boolean not_false(boolean condition) throws Exception {
        if(condition)
            return true;
        throw new Exception("Error");
    }
}
