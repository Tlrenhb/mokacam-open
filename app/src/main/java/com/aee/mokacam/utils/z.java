package com.aee.mokacam.utils;

import com.nostra13.universalimageloader.core.cache.disc.naming.FileNameGenerator;

/** Obfuscated original: file name generator returning uri hash. */
public class z implements FileNameGenerator {
    @Override // FileNameGenerator
    public String generate(String imageUri) {
        return String.valueOf(imageUri.hashCode());
    }
}
