package utils;

import java.time.Instant;

/**
 * @author: ListenYoung
 * @date: Created on 12:38 2023/6/30
 * @modified By:
 */
public class TimeUtils {
  public static Long now() {
    return Instant.now().toEpochMilli();
  }
}
