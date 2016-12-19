package com.chenyoyo.plugin

import com.chenyoyo.adaption.AdaptionExecutor
import org.gradle.api.Plugin
import org.gradle.api.Project

public class PluginImpl implements Plugin<Project> {
    @Override
    void apply(Project project) {
       String path = project.path ;
        System.out.println("project path:"+path) ;
        project.task('execute') << {
            AdaptionExecutor.execute(path.replace(":","")) ;
        }
    }
}