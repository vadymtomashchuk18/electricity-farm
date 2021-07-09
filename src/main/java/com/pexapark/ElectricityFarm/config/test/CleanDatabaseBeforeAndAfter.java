package com.pexapark.ElectricityFarm.config.test;

import com.github.database.rider.core.api.dataset.DataSet;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Metadata annotation for Database Rider */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@DataSet
public @interface CleanDatabaseBeforeAndAfter {

  /**
   * This is an alias for {@link DataSet}.value.
   *
   * @return list of dataset file names using resources folder as root directory. Single dataset
   *     with multiple comma separated dataset file names can also be provided.
   */
  @AliasFor(annotation = DataSet.class, attribute = "value")
  String[] value() default "";

  /**
   * This is an alias for {@link DataSet}.executeScriptsBefore.
   *
   * @return a list of sql script files to execute after test. Note that commands inside sql file
   *     must be separated by ';'
   */
  @AliasFor(annotation = DataSet.class, attribute = "executeScriptsBefore")
  String[] executeScriptsBefore() default "clean_tables.sql";

  /**
   * This is an alias for {@link DataSet}.executeScriptsAfter.
   *
   * @return a list of sql script files to execute after test. Note that commands inside sql file
   *     must be separated by ';'
   */
  @AliasFor(annotation = DataSet.class, attribute = "executeScriptsAfter")
  String[] executeScriptsAfter() default "clean_tables.sql";
}
