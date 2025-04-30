/**
 * PropertyUtils.java
 */
package com.njusc.npm.utils.util;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Semaphore;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author jinzf
 * @date 2013年8月15日
 * @description 配置文件池，对property文件进行管理
 * @version 1.0
 */
public final class PropertyUtils {

    protected final static Logger log = LoggerFactory
            .getLogger(PropertyUtils.class);

    private Map<String, FileContext> pool = new HashMap<String, FileContext>();

    private final Semaphore lock = new Semaphore(1);

    private static volatile  PropertyUtils Instance;

    public static PropertyUtils getNewInstance() {
        if (Instance == null) {
            synchronized (PropertyUtils.class) {
                if (Instance == null) {
                    Instance = new PropertyUtils();
                }
            }
        }
        return Instance;
    }

    private class FileContext {
        final String name;
        final String fPath;
        final Properties p;

        public FileContext(String name, String fPath, Properties p) {
            this.name = name;
            this.fPath = fPath;
            this.p = p;
        }
    }

    private PropertyUtils() {
        String dir = this.getClass().getResource("/").getPath();
        log.debug("PropertyUtils 初始化读配置文件地址" + dir);
        this.loads(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return "conf"
                        .equalsIgnoreCase(FilenameUtils.getExtension(f.getName()));
            }
        }, dir);
    }

    public void loads(final FileFilter filter, final String fPath) {
        File path = new File(fPath);
        File[] list = path.listFiles(filter);
        if (list != null) {
            for (File f : list) {
                load(f, false);
            }
        }
    }

    public boolean load(final String fPath) {
        return load(fPath, false);
    }

    public boolean load(final String fPath, final boolean reload) {
        if (fPath == null || "".equals(fPath.trim())
                || (!reload && pool.containsKey(FilenameUtils.getName(fPath)))) {
            return false;
        }
        final File f = new File(fPath);
        if (f.exists() && f.isFile()
                && (!pool.containsKey(f.getName()) || reload)) {
            Properties p = new Properties();
            FileReader fr = null;
            try {
                fr = new FileReader(f);
                p.load(fr);
                lock.acquire();
                pool.put(f.getName(), new FileContext(f.getName(), fPath, p));
                return true;
            } catch (FileNotFoundException e) {
                log.error(e.getMessage(), e);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            } finally {
                lock.release();
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return false;
    }

    public boolean load(final File f, final boolean reload) {
        if (f == null) {
            return false;
        }
        if (f.exists() && f.isFile()
                && (!pool.containsKey(f.getName()) || reload)) {
            Properties p = new Properties();
            FileReader fr = null;
            try {
                fr = new FileReader(f);
                p.load(fr);
                lock.acquire();
                pool.put(f.getName(),
                        new FileContext(f.getName(), f.getAbsolutePath(), p));
                return true;
            } catch (FileNotFoundException e) {
                log.error(e.getMessage(), e);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            } finally {
                lock.release();
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return false;
    }

    public boolean reload(final String name) {
        if (name == null || "".equals(name.trim())
                || !pool.containsKey(FilenameUtils.getName(name))) {
            return false;
        }
        FileContext fc = pool.get(FilenameUtils.getName(name));
        final File f = new File(fc.fPath);
        if (f.exists() && f.isFile()) {
            Properties p = new Properties();
            FileReader fr = null;
            try {
                fr = new FileReader(f);
                p.load(fr);
                lock.acquire();
                pool.put(fc.name, new FileContext(fc.name, fc.fPath, p));
                return true;
            } catch (FileNotFoundException e) {
                log.error(e.getMessage(), e);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            } finally {
                lock.release();
                try {
                    if (fr != null) {
                        fr.close();
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return false;
    }

    public Properties getProperty(final String fPath) {
        FileContext fc = pool.get(FilenameUtils.getName(fPath));
        return fc != null ? fc.p : null;
    }

    public String getValue(String name, String key, String defualtValue) {
        if (name == null || key == null || "".equals(name.trim())
                || "".equals(key.trim())) {
            return "";
        }
        FileContext fc = pool.get(FilenameUtils.getName(name));
        return fc.p.getProperty(key, defualtValue);
    }

    public String getValue(String key) {
        if (key == null || "".equals(key.trim())) {
            return "";
        }

        for (Map.Entry<String, FileContext> e : pool.entrySet()) {
            if (e.getValue().p.containsKey(key)) {
                return e.getValue().p.getProperty(key);
            }
        }
        return "";
    }

    public String getValue(String key, String defualtValue) {
        if (key == null || "".equals(key.trim())) {
            return "";
        }

        for (Map.Entry<String, FileContext> e : pool.entrySet()) {
            if (e.getValue().p.containsKey(key)) {
                return e.getValue().p.getProperty(key, defualtValue);
            }
        }
        return defualtValue;
    }
   
}
