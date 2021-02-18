package dev.stasys.numberassignservice

import java.nio.file.Path
import java.nio.file.Paths

class TestEnv {
    public static final Path RESOURCE_DIR = Paths.get("src","test","resources").toAbsolutePath()
    static def EMPTY_NUMBERS_FILE_PATH = RESOURCE_DIR.resolve('numbers_empty.gz')
    static def NUMBER_FILE_PATH = RESOURCE_DIR.resolve('numbers.gz')
}
