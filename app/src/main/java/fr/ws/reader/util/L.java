package fr.ws.reader.util;

import android.os.Environment;
import android.text.format.Time;
import android.util.Log;

import fr.ws.reader.app.Constants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Log封装工具类
 */
public class L {
    //TAG打印log标记
    private static final String DEFAULT_TAG = "Lci Grossiste";
    //LOG开关
    private static final boolean isLogOn = Constants.IS_LOG_ON;
    // 是否将日志写入文件
    private static final boolean isFileLogOn = Constants.IS_LOG_TO_FILE;
    // 日志文件保存的文件夹目录
    private static final String LOG_DIR_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    // 当前写入的日志文件
    private static File mCurrentLogFile = null;
    // 日志最大保存天数
    private static final int MAX_LOG_FILE_SAVE_DAYS = 7;

    /**
     * 打印Info信息
     *
     * @param msg
     */
    public static void i(String msg) {
        i(DEFAULT_TAG, msg);
    }

    public static void i(String tag, String msg) {
        i(tag, msg, null);
    }

    public static void i(Throwable tr) {
        i(DEFAULT_TAG, tr);
    }

    public static void i(String msg, Throwable tr) {
        i(DEFAULT_TAG, msg, tr);
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (isLogOn) {
            println(Log.INFO, tag, msg, tr);
        }
    }

    /**
     * 打印debug信息
     *
     * @param msg
     */
    public static void d(String msg) {
        d(DEFAULT_TAG, msg);
    }

    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }

    public static void d(Throwable tr) {
        d(DEFAULT_TAG, tr);
    }

    public static void d(String msg, Throwable tr) {
        d(DEFAULT_TAG, msg, tr);
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (isLogOn) {
            println(Log.DEBUG, tag, msg, tr);
        }
    }

    /**
     * 打印Warning信息
     *
     * @param msg
     */
    public static void w(String msg) {
        w(DEFAULT_TAG, msg);
    }

    public static void w(String tag, String msg) {
        w(tag, msg, null);
    }

    public static void w(Throwable tr) {
        w(DEFAULT_TAG, tr);
    }

    public static void w(String msg, Throwable tr) {
        w(DEFAULT_TAG, msg, tr);
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (isLogOn) {
            println(Log.WARN, tag, msg, tr);
        }
    }

    /**
     * 打印Error信息
     *
     * @param msg
     */
    public static void e(String msg) {
        e(DEFAULT_TAG, msg);
    }

    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }

    public static void e(Throwable tr) {
        e(DEFAULT_TAG, tr);
    }

    public static void e(String msg, Throwable tr) {
        e(DEFAULT_TAG, msg, tr);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (isLogOn) {
            println(Log.ERROR, tag, msg, tr);
        }
    }

    public static void v(String msg) {
        v(DEFAULT_TAG, msg);
    }

    public static void v(String tag, String msg) {
        v(tag, msg, null);
    }

    public static void v(Throwable tr) {
        v(DEFAULT_TAG, tr);
    }

    public static void v(String msg, Throwable tr) {
        v(DEFAULT_TAG, msg, tr);
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (isLogOn) {
            println(Log.VERBOSE, tag, msg, tr);
        }
    }

    private final synchronized static void println(int priority, String tag, String msg, Throwable tr) {
        if (isLogOn) {
            String message = msg + '\n' + Log.getStackTraceString(tr);
            Log.println(priority, tag, message);
            if (isFileLogOn) {
                writeToFile(priority, tag, message);
            }
        }
    }

    /**
     * 将日志写入文件中
     *
     * @param priority
     * @param tag
     * @param msg
     */
    private static void writeToFile(int priority, String tag, String msg) {
        initLogFile();
        if (mCurrentLogFile != null) {
            BufferedWriter bufWriter = null;
            OutputStreamWriter out = null;
            try {
                out = new OutputStreamWriter(new FileOutputStream(mCurrentLogFile), "UTF-8");
                bufWriter = new BufferedWriter(out);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Date date = new Date(System.currentTimeMillis());
                int length = tag.length();
                if (length < 20) {
                    int spaceLength = 20 - length;
                    for (int i = 0; i < spaceLength; i++) {
                        tag += " ";
                    }
                } else if (length > 20) {
                    tag = tag.substring(0, 20);
                }
                String priorityName = null;
                if (priority == Log.VERBOSE) {
                    priorityName = "V";
                } else if (priority == Log.INFO) {
                    priorityName = "I";
                } else if (priority == Log.DEBUG) {
                    priorityName = "D";
                } else if (priority == Log.WARN) {
                    priorityName = "W";
                } else if (priority == Log.ERROR) {
                    priorityName = "E";
                } else {
                    priorityName = "V";
                }
                bufWriter.write(format.format(date) + ":  " + priorityName + "/" + tag + ":  " + msg);
                bufWriter.newLine();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bufWriter != null) {
                    try {
                        bufWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 初始化日志文件
     */
    private static void initLogFile() {
        Time time = new Time(Time.getCurrentTimezone());
        time.set(System.currentTimeMillis());
        String fileName = "LOG-" + time.year + "-" + (time.month + 1) + "-" + time.monthDay + ".log";
        if (mCurrentLogFile == null) {
            mCurrentLogFile = createLogFile(fileName);
        } else if (mCurrentLogFile != null && !mCurrentLogFile.getName().equals(fileName)) {
            mCurrentLogFile = createLogFile(fileName);
        }
    }

    /**
     * 创建SD卡日志文件
     *
     * @param fileName
     * @return
     */
    private static File createLogFile(String fileName) {
        try {
            File logDir = new File(LOG_DIR_PATH, "Log");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            if (logDir.exists()) {
                File[] files = logDir.listFiles();
                if (files != null && files.length >= MAX_LOG_FILE_SAVE_DAYS) {
                    ArrayList<File> fileList = new ArrayList<File>();
                    for (File file : files) {
                        String name = file.getName();
                        if (name.startsWith("LOG-") && name.endsWith(".log")) {
                            fileList.add(file);
                        } else {
                            file.delete();
                        }
                    }
                    if (fileList.size() >= MAX_LOG_FILE_SAVE_DAYS) {
                        Collections.sort(fileList, new Comparator<File>() {
                            @Override
                            public int compare(File f1, File f2) {
                                if (f1.lastModified() > f2.lastModified()) {
                                    return 1;
                                } else if (f1.lastModified() > f2.lastModified()) {
                                    return -1;
                                } else {
                                    return 0;
                                }
                            }
                        });
                        int overflowCount = fileList.size() - MAX_LOG_FILE_SAVE_DAYS + 1;
                        for (int i = 0; i < overflowCount; i++) {
                            if (i >= 0 && i < fileList.size()) {
                                fileList.get(i).delete();
                            }
                        }
                    }
                }
                File currentLogFile = new File(logDir, fileName);
                if (!currentLogFile.exists()) {
                    currentLogFile.createNewFile();
                }
                if (currentLogFile.exists()) {
                    return currentLogFile;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
