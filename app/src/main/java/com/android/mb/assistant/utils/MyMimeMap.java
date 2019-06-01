package com.android.mb.assistant.utils;

import java.util.HashMap;

public class MyMimeMap {
    private static final HashMap<String, String> mapSimple = new HashMap<>();
    private static final HashMap<String, String> mapAll = new HashMap<>();
    /**
     *  常用"文件扩展名—MIME类型"匹配表。
     *  注意，此表并不全，也并不是唯一的，就像有人喜欢用浏览器打开TXT一样，你可以根据自己的爱好自定义。
     */
    public static HashMap<String, String> getMimeMap() {
        if (mapSimple.size() == 0) {
            mapSimple.put(".3gp", "video/3gpp");
            mapSimple.put(".apk", "application/vnd.android.package-archive");
            mapSimple.put(".asf", "video/x-ms-asf");
            mapSimple.put(".avi", "video/x-msvideo");
            mapSimple.put(".bin", "application/octet-stream");
            mapSimple.put(".bmp", "image/bmp");
            mapSimple.put(".c", "text/plain");
            mapSimple.put(".chm", "application/x-chm");
            mapSimple.put(".class", "application/octet-stream");
            mapSimple.put(".conf", "text/plain");
            mapSimple.put(".cpp", "text/plain");
            mapSimple.put(".doc", "application/msword");
            mapSimple.put(".docx", "application/msword");
            mapSimple.put(".exe", "application/octet-stream");
            mapSimple.put(".gif", "image/gif");
            mapSimple.put(".gtar", "application/x-gtar");
            mapSimple.put(".gz", "application/x-gzip");
            mapSimple.put(".h", "text/plain");
            mapSimple.put(".htm", "text/html");
            mapSimple.put(".html", "text/html");
            mapSimple.put(".jar", "application/java-archive");
            mapSimple.put(".java", "text/plain");
            mapSimple.put(".jpeg", "image/jpeg");
            mapSimple.put(".jpg", "image/jpeg");
            mapSimple.put(".js", "application/x-javascript");
            mapSimple.put(".log", "text/plain");
            mapSimple.put(".m3u", "audio/x-mpegurl");
            mapSimple.put(".m4a", "audio/mp4a-latm");
            mapSimple.put(".m4b", "audio/mp4a-latm");
            mapSimple.put(".m4p", "audio/mp4a-latm");
            mapSimple.put(".m4u", "video/vnd.mpegurl");
            mapSimple.put(".m4v", "video/x-m4v");
            mapSimple.put(".mov", "video/quicktime");
            mapSimple.put(".mp2", "audio/x-mpeg");
            mapSimple.put(".mp3", "audio/x-mpeg");
            mapSimple.put(".mp4", "video/mp4");
            mapSimple.put(".mpc", "application/vnd.mpohun.certificate");
            mapSimple.put(".mpe", "video/mpeg");
            mapSimple.put(".mpeg", "video/mpeg");
            mapSimple.put(".mpg", "video/mpeg");
            mapSimple.put(".mpg4", "video/mp4");
            mapSimple.put(".mpga", "audio/mpeg");
            mapSimple.put(".msg", "application/vnd.ms-outlook");
            mapSimple.put(".ogg", "audio/ogg");
            mapSimple.put(".pdf", "application/pdf");
            mapSimple.put(".png", "image/png");
            mapSimple.put(".pps", "application/vnd.ms-powerpoint");
            mapSimple.put(".ppt", "application/vnd.ms-powerpoint");
            mapSimple.put(".pptx", "application/vnd.ms-powerpoint");
            mapSimple.put(".prop", "text/plain");
            mapSimple.put(".rar", "application/x-rar-compressed");
            mapSimple.put(".rc", "text/plain");
            mapSimple.put(".rmvb", "audio/x-pn-realaudio");
            mapSimple.put(".rtf", "application/rtf");
            mapSimple.put(".sh", "text/plain");
            mapSimple.put(".tar", "application/x-tar");
            mapSimple.put(".tgz", "application/x-compressed");
            mapSimple.put(".txt", "text/plain");
            mapSimple.put(".wav", "audio/x-wav");
            mapSimple.put(".wma", "audio/x-ms-wma");
            mapSimple.put(".wmv", "audio/x-ms-wmv");
            mapSimple.put(".wps", "application/vnd.ms-works");
            mapSimple.put(".xml", "text/plain");
            mapSimple.put(".xls", "application/vnd.ms-excel");
            mapSimple.put(".xlsx", "application/vnd.ms-excel");
            mapSimple.put(".z", "application/x-compress");
            mapSimple.put(".zip", "application/zip");
            mapSimple.put("", "*/*");
        }
        return mapSimple;
    }
    /**
     *  常用"文件扩展名—MIME类型"匹配表。
     *  注意，此表并不全，也并不是唯一的，就像有人喜欢用浏览器打开TXT一样，你可以根据自己的爱好自定义。
     */
    public static HashMap<String, String> getMimeMapAll() {
        if (mapAll.size() == 0) {
            mapAll.put("3gp", "video/3gpp");
            mapAll.put("aab", "application/x-authoware-bin");
            mapAll.put("aam", "application/x-authoware-map");
            mapAll.put("aas", "application/x-authoware-seg");
            mapAll.put("ai", "application/postscript");
            mapAll.put("aif", "audio/x-aiff");
            mapAll.put("aifc", "audio/x-aiff");
            mapAll.put("aiff", "audio/x-aiff");
            mapAll.put("als", "audio/X-Alpha5");
            mapAll.put("amc", "application/x-mpeg");
            mapAll.put("ani", "application/octet-stream");
            mapAll.put("apk", "application/vnd.android.package-archive");
            mapAll.put("asc", "text/plain");
            mapAll.put("asd", "application/astound");
            mapAll.put("asf", "video/x-ms-asf");
            mapAll.put("asn", "application/astound");
            mapAll.put("asp", "application/x-asap");
            mapAll.put("asx", "video/x-ms-asf");
            mapAll.put("au", "audio/basic");
            mapAll.put("avb", "application/octet-stream");
            mapAll.put("avi", "video/x-msvideo");
            mapAll.put("awb", "audio/amr-wb");
            mapAll.put("bcpio", "application/x-bcpio");
            mapAll.put("bin", "application/octet-stream");
            mapAll.put("bld", "application/bld");
            mapAll.put("bld2", "application/bld2");
            mapAll.put("bmp", "image/bmp");
            mapAll.put("bpk", "application/octet-stream");
            mapAll.put("bz2", "application/x-bzip2");
            mapAll.put("cal", "image/x-cals");
            mapAll.put("ccn", "application/x-cnc");
            mapAll.put("cco", "application/x-cocoa");
            mapAll.put("cdf", "application/x-netcdf");
            mapAll.put("cgi", "magnus-internal/cgi");
            mapAll.put("chat", "application/x-chat");
            mapAll.put("class", "application/octet-stream");
            mapAll.put("clp", "application/x-msclip");
            mapAll.put("cmx", "application/x-cmx");
            mapAll.put("co", "application/x-cult3d-object");
            mapAll.put("cod", "image/cis-cod");
            mapAll.put("cpio", "application/x-cpio");
            mapAll.put("cpt", "application/mac-compactpro");
            mapAll.put("crd", "application/x-mscardfile");
            mapAll.put("csh", "application/x-csh");
            mapAll.put("csm", "chemical/x-csml");
            mapAll.put("csml", "chemical/x-csml");
            mapAll.put("css", "text/css");
            mapAll.put("cur", "application/octet-stream");
            mapAll.put("dcm", "x-lml/x-evm");
            mapAll.put("dcr", "application/x-director");
            mapAll.put("dcx", "image/x-dcx");
            mapAll.put("dhtml", "text/html");
            mapAll.put("dir", "application/x-director");
            mapAll.put("dll", "application/octet-stream");
            mapAll.put("dmg", "application/octet-stream");
            mapAll.put("dms", "application/octet-stream");
            mapAll.put("doc", "application/msword");
            mapAll.put("dot", "application/x-dot");
            mapAll.put("dvi", "application/x-dvi");
            mapAll.put("dwf", "drawing/x-dwf");
            mapAll.put("dwg", "application/x-autocad");
            mapAll.put("dxf", "application/x-autocad");
            mapAll.put("dxr", "application/x-director");
            mapAll.put("ebk", "application/x-expandedbook");
            mapAll.put("emb", "chemical/x-embl-dl-nucleotide");
            mapAll.put("embl", "chemical/x-embl-dl-nucleotide");
            mapAll.put("eps", "application/postscript");
            mapAll.put("eri", "image/x-eri");
            mapAll.put("es", "audio/echospeech");
            mapAll.put("esl", "audio/echospeech");
            mapAll.put("etc", "application/x-earthtime");
            mapAll.put("etx", "text/x-setext");
            mapAll.put("evm", "x-lml/x-evm");
            mapAll.put("evy", "application/x-envoy");
            mapAll.put("exe", "application/octet-stream");
            mapAll.put("fh4", "image/x-freehand");
            mapAll.put("fh5", "image/x-freehand");
            mapAll.put("fhc", "image/x-freehand");
            mapAll.put("fif", "image/fif");
            mapAll.put("fm", "application/x-maker");
            mapAll.put("fpx", "image/x-fpx");
            mapAll.put("fvi", "video/isivideo");
            mapAll.put("gau", "chemical/x-gaussian-input");
            mapAll.put("gca", "application/x-gca-compressed");
            mapAll.put("gdb", "x-lml/x-gdb");
            mapAll.put("gif", "image/gif");
            mapAll.put("gps", "application/x-gps");
            mapAll.put("gtar", "application/x-gtar");
            mapAll.put("gz", "application/x-gzip");
            mapAll.put("hdf", "application/x-hdf");
            mapAll.put("hdm", "text/x-hdml");
            mapAll.put("hdml", "text/x-hdml");
            mapAll.put("hlp", "application/winhlp");
            mapAll.put("hqx", "application/mac-binhex40");
            mapAll.put("htm", "text/html");
            mapAll.put("html", "text/html");
            mapAll.put("hts", "text/html");
            mapAll.put("ice", "x-conference/x-cooltalk");
            mapAll.put("ico", "application/octet-stream");
            mapAll.put("ief", "image/ief");
            mapAll.put("ifm", "image/gif");
            mapAll.put("ifs", "image/ifs");
            mapAll.put("imy", "audio/melody");
            mapAll.put("ins", "application/x-NET-Install");
            mapAll.put("ips", "application/x-ipscript");
            mapAll.put("ipx", "application/x-ipix");
            mapAll.put("it", "audio/x-mod");
            mapAll.put("itz", "audio/x-mod");
            mapAll.put("ivr", "i-world/i-vrml");
            mapAll.put("j2k", "image/j2k");
            mapAll.put("jad", "text/vnd.sun.j2me.app-descriptor");
            mapAll.put("jam", "application/x-jam");
            mapAll.put("jar", "application/java-archive");
            mapAll.put("jnlp", "application/x-java-jnlp-file");
            mapAll.put("jpe", "image/jpeg");
            mapAll.put("jpeg", "image/jpeg");
            mapAll.put("jpg", "image/jpeg");
            mapAll.put("jpz", "image/jpeg");
            mapAll.put("js", "application/x-javascript");
            mapAll.put("jwc", "application/jwc");
            mapAll.put("kjx", "application/x-kjx");
            mapAll.put("lak", "x-lml/x-lak");
            mapAll.put("latex", "application/x-latex");
            mapAll.put("lcc", "application/fastman");
            mapAll.put("lcl", "application/x-digitalloca");
            mapAll.put("lcr", "application/x-digitalloca");
            mapAll.put("lgh", "application/lgh");
            mapAll.put("lha", "application/octet-stream");
            mapAll.put("lml", "x-lml/x-lml");
            mapAll.put("lmlpack", "x-lml/x-lmlpack");
            mapAll.put("lsf", "video/x-ms-asf");
            mapAll.put("lsx", "video/x-ms-asf");
            mapAll.put("lzh", "application/x-lzh");
            mapAll.put("m13", "application/x-msmediaview");
            mapAll.put("m14", "application/x-msmediaview");
            mapAll.put("m15", "audio/x-mod");
            mapAll.put("m3u", "audio/x-mpegurl");
            mapAll.put("m3url", "audio/x-mpegurl");
            mapAll.put("ma1", "audio/ma1");
            mapAll.put("ma2", "audio/ma2");
            mapAll.put("ma3", "audio/ma3");
            mapAll.put("ma5", "audio/ma5");
            mapAll.put("man", "application/x-troff-man");
            mapAll.put("map", "magnus-internal/imagemap");
            mapAll.put("mbd", "application/mbedlet");
            mapAll.put("mct", "application/x-mascot");
            mapAll.put("mdb", "application/x-msaccess");
            mapAll.put("mdz", "audio/x-mod");
            mapAll.put("me", "application/x-troff-me");
            mapAll.put("mel", "text/x-vmel");
            mapAll.put("mi", "application/x-mif");
            mapAll.put("mid", "audio/midi");
            mapAll.put("midi", "audio/midi");
            mapAll.put("mif", "application/x-mif");
            mapAll.put("mil", "image/x-cals");
            mapAll.put("mio", "audio/x-mio");
            mapAll.put("mmf", "application/x-skt-lbs");
            mapAll.put("mng", "video/x-mng");
            mapAll.put("mny", "application/x-msmoney");
            mapAll.put("moc", "application/x-mocha");
            mapAll.put("mocha", "application/x-mocha");
            mapAll.put("mod", "audio/x-mod");
            mapAll.put("mof", "application/x-yumekara");
            mapAll.put("mol", "chemical/x-mdl-molfile");
            mapAll.put("mop", "chemical/x-mopac-input");
            mapAll.put("mov", "video/quicktime");
            mapAll.put("movie", "video/x-sgi-movie");
            mapAll.put("mp2", "audio/x-mpeg");
            mapAll.put("mp3", "audio/x-mpeg");
            mapAll.put("mp4", "video/mp4");
            mapAll.put("mpc", "application/vnd.mpohun.certificate");
            mapAll.put("mpe", "video/mpeg");
            mapAll.put("mpeg", "video/mpeg");
            mapAll.put("mpg", "video/mpeg");
            mapAll.put("mpg4", "video/mp4");
            mapAll.put("mpga", "audio/mpeg");
            mapAll.put("mpn", "application/vnd.mophun.application");
            mapAll.put("mpp", "application/vnd.ms-project");
            mapAll.put("mps", "application/x-mapserver");
            mapAll.put("mrl", "text/x-mrml");
            mapAll.put("mrm", "application/x-mrm");
            mapAll.put("ms", "application/x-troff-ms");
            mapAll.put("mts", "application/metastream");
            mapAll.put("mtx", "application/metastream");
            mapAll.put("mtz", "application/metastream");
            mapAll.put("mzv", "application/metastream");
            mapAll.put("nar", "application/zip");
            mapAll.put("nbmp", "image/nbmp");
            mapAll.put("nc", "application/x-netcdf");
            mapAll.put("ndb", "x-lml/x-ndb");
            mapAll.put("ndwn", "application/ndwn");
            mapAll.put("nif", "application/x-nif");
            mapAll.put("nmz", "application/x-scream");
            mapAll.put("nokia-op-logo", "image/vnd.nok-oplogo-color");
            mapAll.put("npx", "application/x-netfpx");
            mapAll.put("nsnd", "audio/nsnd");
            mapAll.put("nva", "application/x-neva1");
            mapAll.put("oda", "application/oda");
            mapAll.put("oom", "application/x-AtlasMate-Plugin");
            mapAll.put("pac", "audio/x-pac");
            mapAll.put("pae", "audio/x-epac");
            mapAll.put("pan", "application/x-pan");
            mapAll.put("pbm", "image/x-portable-bitmap");
            mapAll.put("pcx", "image/x-pcx");
            mapAll.put("pda", "image/x-pda");
            mapAll.put("pdb", "chemical/x-pdb");
            mapAll.put("pdf", "application/pdf");
            mapAll.put("pfr", "application/font-tdpfr");
            mapAll.put("pgm", "image/x-portable-graymap");
            mapAll.put("pict", "image/x-pict");
            mapAll.put("pm", "application/x-perl");
            mapAll.put("pmd", "application/x-pmd");
            mapAll.put("png", "image/png");
            mapAll.put("pnm", "image/x-portable-anymap");
            mapAll.put("pnz", "image/png");
            mapAll.put("pot", "application/vnd.ms-powerpoint");
            mapAll.put("ppm", "image/x-portable-pixmap");
            mapAll.put("pps", "application/vnd.ms-powerpoint");
            mapAll.put("ppt", "application/vnd.ms-powerpoint");
            mapAll.put("pqf", "application/x-cprplayer");
            mapAll.put("pqi", "application/cprplayer");
            mapAll.put("prc", "application/x-prc");
            mapAll.put("proxy", "application/x-ns-proxy-autoconfig");
            mapAll.put("ps", "application/postscript");
            mapAll.put("ptlk", "application/listenup");
            mapAll.put("pub", "application/x-mspublisher");
            mapAll.put("pvx", "video/x-pv-pvx");
            mapAll.put("qcp", "audio/vnd.qcelp");
            mapAll.put("qt", "video/quicktime");
            mapAll.put("qti", "image/x-quicktime");
            mapAll.put("qtif", "image/x-quicktime");
            mapAll.put("r3t", "text/vnd.rn-realtext3d");
            mapAll.put("ra", "audio/x-pn-realaudio");
            mapAll.put("ram", "audio/x-pn-realaudio");
            mapAll.put("rar", "application/x-rar-compressed");
            mapAll.put("ras", "image/x-cmu-raster");
            mapAll.put("rdf", "application/rdf+xml");
            mapAll.put("rf", "image/vnd.rn-realflash");
            mapAll.put("rgb", "image/x-rgb");
            mapAll.put("rlf", "application/x-richlink");
            mapAll.put("rm", "audio/x-pn-realaudio");
            mapAll.put("rmf", "audio/x-rmf");
            mapAll.put("rmm", "audio/x-pn-realaudio");
            mapAll.put("rmvb", "audio/x-pn-realaudio");
            mapAll.put("rnx", "application/vnd.rn-realplayer");
            mapAll.put("roff", "application/x-troff");
            mapAll.put("rp", "image/vnd.rn-realpix");
            mapAll.put("rpm", "audio/x-pn-realaudio-plugin");
            mapAll.put("rt", "text/vnd.rn-realtext");
            mapAll.put("rte", "x-lml/x-gps");
            mapAll.put("rtf", "application/rtf");
            mapAll.put("rtg", "application/metastream");
            mapAll.put("rtx", "text/richtext");
            mapAll.put("rv", "video/vnd.rn-realvideo");
            mapAll.put("rwc", "application/x-rogerwilco");
            mapAll.put("s3m", "audio/x-mod");
            mapAll.put("s3z", "audio/x-mod");
            mapAll.put("sca", "application/x-supercard");
            mapAll.put("scd", "application/x-msschedule");
            mapAll.put("sdf", "application/e-score");
            mapAll.put("sea", "application/x-stuffit");
            mapAll.put("sgm", "text/x-sgml");
            mapAll.put("sgml", "text/x-sgml");
            mapAll.put("sh", "application/x-sh");
            mapAll.put("shar", "application/x-shar");
            mapAll.put("shtml", "magnus-internal/parsed-html");
            mapAll.put("shw", "application/presentations");
            mapAll.put("si6", "image/si6");
            mapAll.put("si7", "image/vnd.stiwap.sis");
            mapAll.put("si9", "image/vnd.lgtwap.sis");
            mapAll.put("sis", "application/vnd.symbian.install");
            mapAll.put("sit", "application/x-stuffit");
            mapAll.put("skd", "application/x-Koan");
            mapAll.put("skm", "application/x-Koan");
            mapAll.put("skp", "application/x-Koan");
            mapAll.put("skt", "application/x-Koan");
            mapAll.put("slc", "application/x-salsa");
            mapAll.put("smd", "audio/x-smd");
            mapAll.put("smi", "application/smil");
            mapAll.put("smil", "application/smil");
            mapAll.put("smp", "application/studiom");
            mapAll.put("smz", "audio/x-smd");
            mapAll.put("snd", "audio/basic");
            mapAll.put("spc", "text/x-speech");
            mapAll.put("spl", "application/futuresplash");
            mapAll.put("spr", "application/x-sprite");
            mapAll.put("sprite", "application/x-sprite");
            mapAll.put("spt", "application/x-spt");
            mapAll.put("src", "application/x-wais-source");
            mapAll.put("stk", "application/hyperstudio");
            mapAll.put("stm", "audio/x-mod");
            mapAll.put("sv4cpio", "application/x-sv4cpio");
            mapAll.put("sv4crc", "application/x-sv4crc");
            mapAll.put("svf", "image/vnd");
            mapAll.put("svg", "image/svg-xml");
            mapAll.put("svh", "image/svh");
            mapAll.put("svr", "x-world/x-svr");
            mapAll.put("swf", "application/x-shockwave-flash");
            mapAll.put("swfl", "application/x-shockwave-flash");
            mapAll.put("t", "application/x-troff");
            mapAll.put("tad", "application/octet-stream");
            mapAll.put("talk", "text/x-speech");
            mapAll.put("tar", "application/x-tar");
            mapAll.put("taz", "application/x-tar");
            mapAll.put("tbp", "application/x-timbuktu");
            mapAll.put("tbt", "application/x-timbuktu");
            mapAll.put("tcl", "application/x-tcl");
            mapAll.put("tex", "application/x-tex");
            mapAll.put("texi", "application/x-texinfo");
            mapAll.put("texinfo", "application/x-texinfo");
            mapAll.put("tgz", "application/x-tar");
            mapAll.put("thm", "application/vnd.eri.thm");
            mapAll.put("tif", "image/tiff");
            mapAll.put("tiff", "image/tiff");
            mapAll.put("tki", "application/x-tkined");
            mapAll.put("tkined", "application/x-tkined");
            mapAll.put("toc", "application/toc");
            mapAll.put("toy", "image/toy");
            mapAll.put("tr", "application/x-troff");
            mapAll.put("trk", "x-lml/x-gps");
            mapAll.put("trm", "application/x-msterminal");
            mapAll.put("tsi", "audio/tsplayer");
            mapAll.put("tsp", "application/dsptype");
            mapAll.put("tsv", "text/tab-separated-values");
            mapAll.put("tsv", "text/tab-separated-values");
            mapAll.put("ttf", "application/octet-stream");
            mapAll.put("ttz", "application/t-time");
            mapAll.put("txt", "text/plain");
            mapAll.put("ult", "audio/x-mod");
            mapAll.put("ustar", "application/x-ustar");
            mapAll.put("uu", "application/x-uuencode");
            mapAll.put("uue", "application/x-uuencode");
            mapAll.put("vcd", "application/x-cdlink");
            mapAll.put("vcf", "text/x-vcard");
            mapAll.put("vdo", "video/vdo");
            mapAll.put("vib", "audio/vib");
            mapAll.put("viv", "video/vivo");
            mapAll.put("vivo", "video/vivo");
            mapAll.put("vmd", "application/vocaltec-media-desc");
            mapAll.put("vmf", "application/vocaltec-media-file");
            mapAll.put("vmi", "application/x-dreamcast-vms-info");
            mapAll.put("vms", "application/x-dreamcast-vms");
            mapAll.put("vox", "audio/voxware");
            mapAll.put("vqe", "audio/x-twinvq-plugin");
            mapAll.put("vqf", "audio/x-twinvq");
            mapAll.put("vql", "audio/x-twinvq");
            mapAll.put("vre", "x-world/x-vream");
            mapAll.put("vrml", "x-world/x-vrml");
            mapAll.put("vrt", "x-world/x-vrt");
            mapAll.put("vrw", "x-world/x-vream");
            mapAll.put("vts", "workbook/formulaone");
            mapAll.put("wav", "audio/x-wav");
            mapAll.put("wax", "audio/x-ms-wax");
            mapAll.put("wbmp", "image/vnd.wap.wbmp");
            mapAll.put("web", "application/vnd.xara");
            mapAll.put("wi", "image/wavelet");
            mapAll.put("wis", "application/x-InstallShield");
            mapAll.put("wm", "video/x-ms-wm");
            mapAll.put("wma", "audio/x-ms-wma");
            mapAll.put("wmd", "application/x-ms-wmd");
            mapAll.put("wmf", "application/x-msmetafile");
            mapAll.put("wml", "text/vnd.wap.wml");
            mapAll.put("wmlc", "application/vnd.wap.wmlc");
            mapAll.put("wmls", "text/vnd.wap.wmlscript");
            mapAll.put("wmlsc", "application/vnd.wap.wmlscriptc");
            mapAll.put("wmlscript", "text/vnd.wap.wmlscript");
            mapAll.put("wmv", "audio/x-ms-wmv");
            mapAll.put("wmx", "video/x-ms-wmx");
            mapAll.put("wmz", "application/x-ms-wmz");
            mapAll.put("wpng", "image/x-up-wpng");
            mapAll.put("wpt", "x-lml/x-gps");
            mapAll.put("wri", "application/x-mswrite");
            mapAll.put("wrl", "x-world/x-vrml");
            mapAll.put("wrz", "x-world/x-vrml");
            mapAll.put("ws", "text/vnd.wap.wmlscript");
            mapAll.put("wsc", "application/vnd.wap.wmlscriptc");
            mapAll.put("wv", "video/wavelet");
            mapAll.put("wvx", "video/x-ms-wvx");
            mapAll.put("wxl", "application/x-wxl");
            mapAll.put("x-gzip", "application/x-gzip");
            mapAll.put("xar", "application/vnd.xara");
            mapAll.put("xbm", "image/x-xbitmap");
            mapAll.put("xdm", "application/x-xdma");
            mapAll.put("xdma", "application/x-xdma");
            mapAll.put("xdw", "application/vnd.fujixerox.docuworks");
            mapAll.put("xht", "application/xhtml+xml");
            mapAll.put("xhtm", "application/xhtml+xml");
            mapAll.put("xhtml", "application/xhtml+xml");
            mapAll.put("xla", "application/vnd.ms-excel");
            mapAll.put("xlc", "application/vnd.ms-excel");
            mapAll.put("xll", "application/x-excel");
            mapAll.put("xlm", "application/vnd.ms-excel");
            mapAll.put("xls", "application/vnd.ms-excel");
            mapAll.put("xlt", "application/vnd.ms-excel");
            mapAll.put("xlw", "application/vnd.ms-excel");
            mapAll.put("xm", "audio/x-mod");
            mapAll.put("xml", "text/xml");
            mapAll.put("xmz", "audio/x-mod");
            mapAll.put("xpi", "application/x-xpinstall");
            mapAll.put("xpm", "image/x-xpixmap");
            mapAll.put("xsit", "text/xml");
            mapAll.put("xsl", "text/xml");
            mapAll.put("xul", "text/xul");
            mapAll.put("xwd", "image/x-xwindowdump");
            mapAll.put("xyz", "chemical/x-pdb");
            mapAll.put("yz1", "application/x-yz1");
            mapAll.put("z", "application/x-compress");
            mapAll.put("zac", "application/x-zaurus-zac");
            mapAll.put("zip", "application/zip");
        }
        return mapAll;
    }
}
