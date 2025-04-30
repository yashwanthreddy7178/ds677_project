package com.meizu.cloud.pushsdk.b.c;

import com.taobao.android.dexposed.ClassUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f26917a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with root package name */
    public final String f26918b;

    /* renamed from: c  reason: collision with root package name */
    private final String f26919c;

    /* renamed from: d  reason: collision with root package name */
    private final String f26920d;
    /* access modifiers changed from: private */

    /* renamed from: e  reason: collision with root package name */
    public final String f26921e;
    /* access modifiers changed from: private */

    /* renamed from: f  reason: collision with root package name */
    public final int f26922f;
    private final List<String> g;
    private final List<String> h;
    private final String i;
    private final String j;

    /* renamed from: com.meizu.cloud.pushsdk.b.c.f$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f26923a = new int[a.C0313a.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.meizu.cloud.pushsdk.b.c.f$a$a[] r0 = com.meizu.cloud.pushsdk.b.c.f.a.C0313a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f26923a = r0
                int[] r0 = f26923a     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.meizu.cloud.pushsdk.b.c.f$a$a r1 = com.meizu.cloud.pushsdk.b.c.f.a.C0313a.SUCCESS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f26923a     // Catch:{ NoSuchFieldError -> 0x001f }
                com.meizu.cloud.pushsdk.b.c.f$a$a r1 = com.meizu.cloud.pushsdk.b.c.f.a.C0313a.INVALID_HOST     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f26923a     // Catch:{ NoSuchFieldError -> 0x002a }
                com.meizu.cloud.pushsdk.b.c.f$a$a r1 = com.meizu.cloud.pushsdk.b.c.f.a.C0313a.UNSUPPORTED_SCHEME     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f26923a     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.meizu.cloud.pushsdk.b.c.f$a$a r1 = com.meizu.cloud.pushsdk.b.c.f.a.C0313a.MISSING_SCHEME     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f26923a     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.meizu.cloud.pushsdk.b.c.f$a$a r1 = com.meizu.cloud.pushsdk.b.c.f.a.C0313a.INVALID_PORT     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.b.c.f.AnonymousClass1.<clinit>():void");
        }
    }

    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        String f26924a;

        /* renamed from: b  reason: collision with root package name */
        String f26925b = "";

        /* renamed from: c  reason: collision with root package name */
        String f26926c = "";

        /* renamed from: d  reason: collision with root package name */
        String f26927d;

        /* renamed from: e  reason: collision with root package name */
        int f26928e = -1;

        /* renamed from: f  reason: collision with root package name */
        final List<String> f26929f = new ArrayList();
        List<String> g;
        String h;

        /* renamed from: com.meizu.cloud.pushsdk.b.c.f$a$a  reason: collision with other inner class name */
        enum C0313a {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        public a() {
            this.f26929f.add("");
        }

        private static String a(byte[] bArr) {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            int i4 = -1;
            while (i2 < bArr.length) {
                int i5 = i2;
                while (i5 < 16 && bArr[i5] == 0 && bArr[i5 + 1] == 0) {
                    i5 += 2;
                }
                int i6 = i5 - i2;
                if (i6 > i3) {
                    i4 = i2;
                    i3 = i6;
                }
                i2 = i5 + 2;
            }
            com.meizu.cloud.pushsdk.b.g.a aVar = new com.meizu.cloud.pushsdk.b.g.a();
            while (i < bArr.length) {
                if (i == i4) {
                    aVar.b(58);
                    i += i3;
                    if (i == 16) {
                        aVar.b(58);
                    }
                } else {
                    if (i > 0) {
                        aVar.b(58);
                    }
                    aVar.d((long) (((bArr[i] & 255) << 8) | (bArr[i + 1] & 255)));
                    i += 2;
                }
            }
            return aVar.h();
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        /* JADX WARNING: Removed duplicated region for block: B:10:0x0030  */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0048 A[SYNTHETIC] */
        private void a(java.lang.String r10, int r11, int r12) {
            /*
                r9 = this;
                if (r11 != r12) goto L_0x0003
                return
            L_0x0003:
                char r0 = r10.charAt(r11)
                r1 = 47
                r2 = 1
                if (r0 == r1) goto L_0x0020
                r1 = 92
                if (r0 != r1) goto L_0x0011
                goto L_0x0020
            L_0x0011:
                java.util.List<java.lang.String> r0 = r9.f26929f
                java.util.List<java.lang.String> r1 = r9.f26929f
                int r1 = r1.size()
                int r1 = r1 - r2
                java.lang.String r3 = ""
                r0.set(r1, r3)
                goto L_0x002d
            L_0x0020:
                java.util.List<java.lang.String> r0 = r9.f26929f
                r0.clear()
                java.util.List<java.lang.String> r0 = r9.f26929f
                java.lang.String r1 = ""
                r0.add(r1)
                goto L_0x0045
            L_0x002d:
                r5 = r11
                if (r5 >= r12) goto L_0x0048
                java.lang.String r11 = "/\\"
                int r11 = com.meizu.cloud.pushsdk.b.c.m.a((java.lang.String) r10, (int) r5, (int) r12, (java.lang.String) r11)
                if (r11 >= r12) goto L_0x003a
                r0 = 1
                goto L_0x003b
            L_0x003a:
                r0 = 0
            L_0x003b:
                r8 = 1
                r3 = r9
                r4 = r10
                r6 = r11
                r7 = r0
                r3.a((java.lang.String) r4, (int) r5, (int) r6, (boolean) r7, (boolean) r8)
                if (r0 == 0) goto L_0x002d
            L_0x0045:
                int r11 = r11 + 1
                goto L_0x002d
            L_0x0048:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.b.c.f.a.a(java.lang.String, int, int):void");
        }

        private void a(String str, int i, int i2, boolean z, boolean z2) {
            String a2 = f.a(str, i, i2, " \"<>^`{}|/\\?#", z2, false, false, true);
            if (!b(a2)) {
                if (c(a2)) {
                    c();
                    return;
                }
                if (this.f26929f.get(this.f26929f.size() - 1).isEmpty()) {
                    this.f26929f.set(this.f26929f.size() - 1, a2);
                } else {
                    this.f26929f.add(a2);
                }
                if (z) {
                    this.f26929f.add("");
                }
            }
        }

        private static boolean a(String str, int i, int i2, byte[] bArr, int i3) {
            int i4 = i3;
            while (i < i2) {
                if (i4 == bArr.length) {
                    return false;
                }
                if (i4 != i3) {
                    if (str.charAt(i) != '.') {
                        return false;
                    }
                    i++;
                }
                int i5 = i;
                int i6 = 0;
                while (i5 < i2) {
                    char charAt = str.charAt(i5);
                    if (charAt < '0' || charAt > '9') {
                        break;
                    } else if (i6 == 0 && i != i5) {
                        return false;
                    } else {
                        i6 = ((i6 * 10) + charAt) - 48;
                        if (i6 > 255) {
                            return false;
                        }
                        i5++;
                    }
                }
                if (i5 - i == 0) {
                    return false;
                }
                bArr[i4] = (byte) i6;
                i4++;
                i = i5;
            }
            return i4 == i3 + 4;
        }

        private static int b(String str, int i, int i2) {
            if (i2 - i < 2) {
                return -1;
            }
            char charAt = str.charAt(i);
            if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                while (true) {
                    i++;
                    if (i >= i2) {
                        break;
                    }
                    char charAt2 = str.charAt(i);
                    if ((charAt2 < 'a' || charAt2 > 'z') && ((charAt2 < 'A' || charAt2 > 'Z') && !((charAt2 >= '0' && charAt2 <= '9') || charAt2 == '+' || charAt2 == '-' || charAt2 == '.'))) {
                        if (charAt2 == ':') {
                            return i;
                        }
                    }
                }
            }
            return -1;
        }

        private boolean b(String str) {
            return str.equals(ClassUtils.PACKAGE_SEPARATOR) || str.equalsIgnoreCase("%2e");
        }

        private static int c(String str, int i, int i2) {
            int i3 = 0;
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt != '\\' && charAt != '/') {
                    break;
                }
                i3++;
                i++;
            }
            return i3;
        }

        private void c() {
            if (!this.f26929f.remove(this.f26929f.size() - 1).isEmpty() || this.f26929f.isEmpty()) {
                this.f26929f.add("");
            } else {
                this.f26929f.set(this.f26929f.size() - 1, "");
            }
        }

        private boolean c(String str) {
            return str.equals("..") || str.equalsIgnoreCase("%2e.") || str.equalsIgnoreCase(".%2e") || str.equalsIgnoreCase("%2e%2e");
        }

        private static int d(String str, int i, int i2) {
            while (i < i2) {
                char charAt = str.charAt(i);
                if (charAt == ':') {
                    return i;
                }
                if (charAt == '[') {
                    do {
                        i++;
                        if (i >= i2) {
                            break;
                        }
                    } while (str.charAt(i) != ']');
                }
                i++;
            }
            return i2;
        }

        private static String e(String str, int i, int i2) {
            String a2 = f.a(str, i, i2, false);
            if (!a2.contains(":")) {
                return m.a(a2);
            }
            InetAddress f2 = (!a2.startsWith("[") || !a2.endsWith("]")) ? f(a2, 0, a2.length()) : f(a2, 1, a2.length() - 1);
            if (f2 == null) {
                return null;
            }
            byte[] address = f2.getAddress();
            if (address.length == 16) {
                return a(address);
            }
            throw new AssertionError();
        }

        private static InetAddress f(String str, int i, int i2) {
            byte[] bArr = new byte[16];
            int i3 = 0;
            int i4 = -1;
            int i5 = -1;
            while (true) {
                if (i >= i2) {
                    break;
                } else if (i3 == 16) {
                    return null;
                } else {
                    int i6 = i + 2;
                    if (i6 > i2 || !str.regionMatches(i, "::", 0, 2)) {
                        if (i3 != 0) {
                            if (str.regionMatches(i, ":", 0, 1)) {
                                i++;
                            } else if (!str.regionMatches(i, ClassUtils.PACKAGE_SEPARATOR, 0, 1) || !a(str, i5, i2, bArr, i3 - 2)) {
                                return null;
                            } else {
                                i3 += 2;
                            }
                        }
                        i5 = i;
                    } else if (i4 != -1) {
                        return null;
                    } else {
                        i3 += 2;
                        if (i6 == i2) {
                            i4 = i3;
                            break;
                        }
                        i4 = i3;
                        i5 = i6;
                    }
                    i = i5;
                    int i7 = 0;
                    while (i < i2) {
                        int a2 = f.a(str.charAt(i));
                        if (a2 == -1) {
                            break;
                        }
                        i7 = (i7 << 4) + a2;
                        i++;
                    }
                    int i8 = i - i5;
                    if (i8 == 0 || i8 > 4) {
                        return null;
                    }
                    int i9 = i3 + 1;
                    bArr[i3] = (byte) ((i7 >>> 8) & 255);
                    i3 = i9 + 1;
                    bArr[i9] = (byte) (i7 & 255);
                }
            }
            if (i3 != 16) {
                if (i4 == -1) {
                    return null;
                }
                int i10 = i3 - i4;
                System.arraycopy(bArr, i4, bArr, 16 - i10, i10);
                Arrays.fill(bArr, i4, (16 - i3) + i4, (byte) 0);
            }
            try {
                return InetAddress.getByAddress(bArr);
            } catch (UnknownHostException unused) {
                throw new AssertionError();
            }
        }

        private static int g(String str, int i, int i2) {
            try {
                int parseInt = Integer.parseInt(f.a(str, i, i2, "", false, false, false, true));
                if (parseInt <= 0 || parseInt > 65535) {
                    return -1;
                }
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        }

        /* access modifiers changed from: package-private */
        public final int a() {
            return this.f26928e != -1 ? this.f26928e : f.a(this.f26924a);
        }

        /* access modifiers changed from: package-private */
        public final C0313a a(f fVar, String str) {
            int i;
            int a2;
            int i2;
            String str2 = str;
            int a3 = m.a(str2, 0, str.length());
            int b2 = m.b(str2, a3, str.length());
            if (b(str2, a3, b2) != -1) {
                if (str.regionMatches(true, a3, "https:", 0, 6)) {
                    this.f26924a = "https";
                    a3 += 6;
                } else if (!str.regionMatches(true, a3, "http:", 0, 5)) {
                    return C0313a.UNSUPPORTED_SCHEME;
                } else {
                    this.f26924a = "http";
                    a3 += 5;
                }
            } else if (fVar == null) {
                return C0313a.MISSING_SCHEME;
            } else {
                this.f26924a = fVar.f26918b;
            }
            int c2 = c(str2, a3, b2);
            char c3 = '#';
            if (c2 >= 2 || fVar == null || !fVar.f26918b.equals(this.f26924a)) {
                int i3 = a3 + c2;
                boolean z = false;
                boolean z2 = false;
                while (true) {
                    a2 = m.a(str2, i3, b2, "@/\\?#");
                    char charAt = a2 != b2 ? str2.charAt(a2) : 65535;
                    if (!(charAt == 65535 || charAt == c3 || charAt == '/' || charAt == '\\')) {
                        switch (charAt) {
                            case '?':
                                break;
                            case '@':
                                if (!z) {
                                    int a4 = m.a(str2, i3, a2, ':');
                                    int i4 = a4;
                                    i2 = a2;
                                    String a5 = f.a(str, i3, a4, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                    if (z2) {
                                        a5 = this.f26925b + "%40" + a5;
                                    }
                                    this.f26925b = a5;
                                    if (i4 != i2) {
                                        this.f26926c = f.a(str, i4 + 1, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                        z = true;
                                    }
                                    z2 = true;
                                } else {
                                    i2 = a2;
                                    this.f26926c += "%40" + f.a(str, i3, i2, " \"':;<=>@[]^`{}|/\\?#", true, false, false, true);
                                }
                                i3 = i2 + 1;
                                c3 = '#';
                                continue;
                        }
                    }
                }
                i = a2;
                int d2 = d(str2, i3, i);
                int i5 = d2 + 1;
                if (i5 < i) {
                    this.f26927d = e(str2, i3, d2);
                    this.f26928e = g(str2, i5, i);
                    if (this.f26928e == -1) {
                        return C0313a.INVALID_PORT;
                    }
                } else {
                    this.f26927d = e(str2, i3, d2);
                    this.f26928e = f.a(this.f26924a);
                }
                if (this.f26927d == null) {
                    return C0313a.INVALID_HOST;
                }
            } else {
                this.f26925b = fVar.b();
                this.f26926c = fVar.c();
                this.f26927d = fVar.f26921e;
                this.f26928e = fVar.f26922f;
                this.f26929f.clear();
                this.f26929f.addAll(fVar.d());
                if (a3 == b2 || str2.charAt(a3) == '#') {
                    a(fVar.e());
                }
                i = a3;
            }
            int a6 = m.a(str2, i, b2, "?#");
            a(str2, i, a6);
            if (a6 < b2 && str2.charAt(a6) == '?') {
                int a7 = m.a(str2, a6, b2, '#');
                this.g = f.b(f.a(str, a6 + 1, a7, " \"'<>#", true, false, true, true));
                a6 = a7;
            }
            if (a6 < b2 && str2.charAt(a6) == '#') {
                this.h = f.a(str, 1 + a6, b2, "", true, false, false, false);
            }
            return C0313a.SUCCESS;
        }

        public final a a(String str) {
            this.g = str != null ? f.b(f.a(str, " \"'<>#", true, false, true, true)) : null;
            return this;
        }

        public final a a(String str, String str2) {
            if (str != null) {
                if (this.g == null) {
                    this.g = new ArrayList();
                }
                this.g.add(f.a(str, " \"'<>#&=", false, false, true, true));
                this.g.add(str2 != null ? f.a(str2, " \"'<>#&=", false, false, true, true) : null);
                return this;
            }
            throw new IllegalArgumentException("name == null");
        }

        public final f b() {
            if (this.f26924a == null) {
                throw new IllegalStateException("scheme == null");
            } else if (this.f26927d != null) {
                return new f(this, null);
            } else {
                throw new IllegalStateException("host == null");
            }
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f26924a);
            sb.append("://");
            if (!this.f26925b.isEmpty() || !this.f26926c.isEmpty()) {
                sb.append(this.f26925b);
                if (!this.f26926c.isEmpty()) {
                    sb.append(':');
                    sb.append(this.f26926c);
                }
                sb.append('@');
            }
            if (this.f26927d.indexOf(58) != -1) {
                sb.append('[');
                sb.append(this.f26927d);
                sb.append(']');
            } else {
                sb.append(this.f26927d);
            }
            int a2 = a();
            if (a2 != f.a(this.f26924a)) {
                sb.append(':');
                sb.append(a2);
            }
            f.a(sb, this.f26929f);
            if (this.g != null) {
                sb.append('?');
                f.b(sb, this.g);
            }
            if (this.h != null) {
                sb.append('#');
                sb.append(this.h);
            }
            return sb.toString();
        }
    }

    private f(a aVar) {
        this.f26918b = aVar.f26924a;
        this.f26919c = a(aVar.f26925b, false);
        this.f26920d = a(aVar.f26926c, false);
        this.f26921e = aVar.f26927d;
        this.f26922f = aVar.a();
        this.g = a(aVar.f26929f, false);
        String str = null;
        this.h = aVar.g != null ? a(aVar.g, true) : null;
        this.i = aVar.h != null ? a(aVar.h, false) : str;
        this.j = aVar.toString();
    }

    /* synthetic */ f(a aVar, AnonymousClass1 r2) {
        this(aVar);
    }

    static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - '0';
        }
        char c3 = 'a';
        if (c2 < 'a' || c2 > 'f') {
            c3 = 'A';
            if (c2 < 'A' || c2 > 'F') {
                return -1;
            }
        }
        return (c2 - c3) + 10;
    }

    public static int a(String str) {
        if (str.equals("http")) {
            return 80;
        }
        return str.equals("https") ? 443 : -1;
    }

    static String a(String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        String str3 = str;
        int i4 = i3;
        int i5 = i2;
        while (i5 < i4) {
            int codePointAt = str.codePointAt(i5);
            if (codePointAt < 32 || codePointAt == 127 || (codePointAt >= 128 && z4)) {
                String str4 = str2;
            } else {
                String str5 = str2;
                if (str2.indexOf(codePointAt) == -1 && ((codePointAt != 37 || (z && (!z2 || a(str, i5, i3)))) && (codePointAt != 43 || !z3))) {
                    i5 += Character.charCount(codePointAt);
                }
            }
            com.meizu.cloud.pushsdk.b.g.a aVar = new com.meizu.cloud.pushsdk.b.g.a();
            int i6 = i2;
            aVar.a(str, i2, i5);
            a(aVar, str, i5, i3, str2, z, z2, z3, z4);
            return aVar.h();
        }
        int i7 = i2;
        return str.substring(i2, i3);
    }

    static String a(String str, int i2, int i3, boolean z) {
        for (int i4 = i2; i4 < i3; i4++) {
            char charAt = str.charAt(i4);
            if (charAt == '%' || (charAt == '+' && z)) {
                com.meizu.cloud.pushsdk.b.g.a aVar = new com.meizu.cloud.pushsdk.b.g.a();
                aVar.a(str, i2, i4);
                a(aVar, str, i4, i3, z);
                return aVar.h();
            }
        }
        return str.substring(i2, i3);
    }

    static String a(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        return a(str, 0, str.length(), str2, z, z2, z3, z4);
    }

    static String a(String str, boolean z) {
        return a(str, 0, str.length(), z);
    }

    private List<String> a(List<String> list, boolean z) {
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it2 = list.iterator();
        while (it2.hasNext()) {
            String next = it2.next();
            arrayList.add(next != null ? a(next, z) : null);
        }
        return Collections.unmodifiableList(arrayList);
    }

    static void a(com.meizu.cloud.pushsdk.b.g.a aVar, String str, int i2, int i3, String str2, boolean z, boolean z2, boolean z3, boolean z4) {
        com.meizu.cloud.pushsdk.b.g.a aVar2 = null;
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (!z || !(codePointAt == 9 || codePointAt == 10 || codePointAt == 12 || codePointAt == 13)) {
                if (codePointAt == 43 && z3) {
                    aVar.b(z ? "+" : "%2B");
                } else if (codePointAt < 32 || codePointAt == 127 || ((codePointAt >= 128 && z4) || str2.indexOf(codePointAt) != -1 || (codePointAt == 37 && (!z || (z2 && !a(str, i2, i3)))))) {
                    if (aVar2 == null) {
                        aVar2 = new com.meizu.cloud.pushsdk.b.g.a();
                    }
                    aVar2.a(codePointAt);
                    while (!aVar2.c()) {
                        byte f2 = aVar2.f() & 255;
                        aVar.b(37);
                        aVar.b((int) f26917a[(f2 >> 4) & 15]);
                        aVar.b((int) f26917a[f2 & 15]);
                    }
                } else {
                    aVar.a(codePointAt);
                }
            }
            i2 += Character.charCount(codePointAt);
        }
    }

    static void a(com.meizu.cloud.pushsdk.b.g.a aVar, String str, int i2, int i3, boolean z) {
        while (i2 < i3) {
            int codePointAt = str.codePointAt(i2);
            if (codePointAt == 37) {
                int i4 = i2 + 2;
                if (i4 < i3) {
                    int a2 = a(str.charAt(i2 + 1));
                    int a3 = a(str.charAt(i4));
                    if (!(a2 == -1 || a3 == -1)) {
                        aVar.b((a2 << 4) + a3);
                        i2 = i4;
                        i2 += Character.charCount(codePointAt);
                    }
                    aVar.a(codePointAt);
                    i2 += Character.charCount(codePointAt);
                }
            }
            if (codePointAt == 43 && z) {
                aVar.b(32);
                i2 += Character.charCount(codePointAt);
            }
            aVar.a(codePointAt);
            i2 += Character.charCount(codePointAt);
        }
    }

    static void a(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append(list.get(i2));
        }
    }

    static boolean a(String str, int i2, int i3) {
        int i4 = i2 + 2;
        return i4 < i3 && str.charAt(i2) == '%' && a(str.charAt(i2 + 1)) != -1 && a(str.charAt(i4)) != -1;
    }

    static List<String> b(String str) {
        String str2;
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 <= str.length()) {
            int indexOf = str.indexOf(38, i2);
            if (indexOf == -1) {
                indexOf = str.length();
            }
            int indexOf2 = str.indexOf(61, i2);
            if (indexOf2 == -1 || indexOf2 > indexOf) {
                arrayList.add(str.substring(i2, indexOf));
                str2 = null;
            } else {
                arrayList.add(str.substring(i2, indexOf2));
                str2 = str.substring(indexOf2 + 1, indexOf);
            }
            arrayList.add(str2);
            i2 = indexOf + 1;
        }
        return arrayList;
    }

    static void b(StringBuilder sb, List<String> list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2 += 2) {
            String str = list.get(i2);
            String str2 = list.get(i2 + 1);
            if (i2 > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
        }
    }

    public static f c(String str) {
        a aVar = new a();
        if (aVar.a((f) null, str) == a.C0313a.SUCCESS) {
            return aVar.b();
        }
        return null;
    }

    public boolean a() {
        return this.f26918b.equals("https");
    }

    public String b() {
        if (this.f26919c.isEmpty()) {
            return "";
        }
        int length = this.f26918b.length() + 3;
        String str = this.j;
        return this.j.substring(length, m.a(str, length, str.length(), ":@"));
    }

    public String c() {
        if (this.f26920d.isEmpty()) {
            return "";
        }
        int indexOf = this.j.indexOf(64);
        return this.j.substring(this.j.indexOf(58, this.f26918b.length() + 3) + 1, indexOf);
    }

    public List<String> d() {
        int indexOf = this.j.indexOf(47, this.f26918b.length() + 3);
        String str = this.j;
        int a2 = m.a(str, indexOf, str.length(), "?#");
        ArrayList arrayList = new ArrayList();
        while (indexOf < a2) {
            int i2 = indexOf + 1;
            int a3 = m.a(this.j, i2, a2, '/');
            arrayList.add(this.j.substring(i2, a3));
            indexOf = a3;
        }
        return arrayList;
    }

    public String e() {
        if (this.h == null) {
            return null;
        }
        int indexOf = this.j.indexOf(63) + 1;
        return this.j.substring(indexOf, m.a(this.j, indexOf + 1, this.j.length(), '#'));
    }

    public boolean equals(Object obj) {
        return (obj instanceof f) && ((f) obj).j.equals(this.j);
    }

    public String f() {
        if (this.i == null) {
            return null;
        }
        return this.j.substring(this.j.indexOf(35) + 1);
    }

    public a g() {
        a aVar = new a();
        aVar.f26924a = this.f26918b;
        aVar.f26925b = b();
        aVar.f26926c = c();
        aVar.f26927d = this.f26921e;
        aVar.f26928e = this.f26922f != a(this.f26918b) ? this.f26922f : -1;
        aVar.f26929f.clear();
        aVar.f26929f.addAll(d());
        aVar.a(e());
        aVar.h = f();
        return aVar;
    }

    public int hashCode() {
        return this.j.hashCode();
    }

    public String toString() {
        return this.j;
    }
}
