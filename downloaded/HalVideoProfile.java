// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.xfinity.playerlib.model.consumable.hal;

import com.comcast.cim.cmasl.hal.model.HalParseable;
import com.comcast.cim.cmasl.hal.model.MicrodataPropertyResolver;

public class HalVideoProfile
    implements HalParseable
{

    private long bitrate;
    private long contentSizeInBytes;
    private int height;
    private String manifestUrl;
    private String secData;
    private int width;

    public HalVideoProfile()
    {
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (HalVideoProfile)obj;
            if (bitrate != ((HalVideoProfile) (obj)).bitrate)
            {
                return false;
            }
            if (contentSizeInBytes != ((HalVideoProfile) (obj)).contentSizeInBytes)
            {
                return false;
            }
            if (height != ((HalVideoProfile) (obj)).height)
            {
                return false;
            }
            if (width != ((HalVideoProfile) (obj)).width)
            {
                return false;
            }
            if (manifestUrl == null ? ((HalVideoProfile) (obj)).manifestUrl != null : !manifestUrl.equals(((HalVideoProfile) (obj)).manifestUrl))
            {
                return false;
            }
            if (secData == null ? ((HalVideoProfile) (obj)).secData != null : !secData.equals(((HalVideoProfile) (obj)).secData))
            {
                return false;
            }
        }
        return true;
    }

    public long getBitrate()
    {
        return bitrate;
    }

    public long getContentSizeInBytes()
    {
        return contentSizeInBytes;
    }

    public int getHeight()
    {
        return height;
    }

    public String getManifestUrl()
    {
        return manifestUrl;
    }

    public String getSecData()
    {
        return secData;
    }

    public int getWidth()
    {
        return width;
    }

    public int hashCode()
    {
        int j = 0;
        int i;
        if (manifestUrl != null)
        {
            i = manifestUrl.hashCode();
        } else
        {
            i = 0;
        }
        if (secData != null)
        {
            j = secData.hashCode();
        }
        return ((((i * 31 + j) * 31 + height) * 31 + width) * 31 + (int)(contentSizeInBytes ^ contentSizeInBytes >>> 32)) * 31 + (int)(bitrate ^ bitrate >>> 32);
    }

    public void parseHalContent(MicrodataPropertyResolver microdatapropertyresolver)
    {
        manifestUrl = microdatapropertyresolver.fetchLinkAsString("manifest");
        secData = microdatapropertyresolver.fetchString("secData");
        String s1 = microdatapropertyresolver.fetchOptionalString("height");
        String s = s1;
        if (s1 == null)
        {
            s = "0";
        }
        height = Integer.parseInt(s);
        s1 = microdatapropertyresolver.fetchOptionalString("width");
        s = s1;
        if (s1 == null)
        {
            s = "0";
        }
        width = Integer.parseInt(s);
        s1 = microdatapropertyresolver.fetchOptionalString("contentSize");
        s = s1;
        if (s1 == null)
        {
            s = "0";
        }
        contentSizeInBytes = Long.parseLong(s);
        s = microdatapropertyresolver.fetchOptionalString("bitrate");
        microdatapropertyresolver = s;
        if (s == null)
        {
            microdatapropertyresolver = "0";
        }
        bitrate = Long.parseLong(microdatapropertyresolver);
    }
}
