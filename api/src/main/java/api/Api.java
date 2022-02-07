package api;

import core.Core;
import lombok.Data;

@Data
public class Api {
    private Core core;
    private Str str;
    
    @Data
    public static class Str {
        private String str;
    }
}
