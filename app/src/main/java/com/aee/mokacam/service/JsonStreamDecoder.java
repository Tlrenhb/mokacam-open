package com.aee.mokacam.service;

/** Extracts complete JSON objects from a TCP byte stream with TCP coalescing/splitting. */
final class JsonStreamDecoder {
    private final StringBuilder buffer = new StringBuilder();

    synchronized void append(String chunk) {
        if (chunk != null && !chunk.isEmpty()) buffer.append(chunk);
    }

    synchronized String poll() {
        int start = -1;
        for (int i = 0; i < buffer.length(); i++) {
            if (buffer.charAt(i) == '{') { start = i; break; }
        }
        if (start < 0) {
            buffer.setLength(0);
            return null;
        }
        if (start > 0) buffer.delete(0, start);
        int depth = 0;
        boolean quoted = false;
        boolean escaped = false;
        for (int i = 0; i < buffer.length(); i++) {
            char ch = buffer.charAt(i);
            if (quoted) {
                if (escaped) escaped = false;
                else if (ch == '\\') escaped = true;
                else if (ch == '"') quoted = false;
                continue;
            }
            if (ch == '"') { quoted = true; continue; }
            if (ch == '{') depth++;
            else if (ch == '}' && --depth == 0) {
                String result = buffer.substring(0, i + 1);
                buffer.delete(0, i + 1);
                return result;
            }
        }
        return null;
    }
}
