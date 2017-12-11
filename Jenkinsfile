#!/usr/bin/env groovy

@Library('ZomisJenkins')
import net.zomis.jenkins.Duga

node {
    stage('Checkout') {
        checkout scm
    }

    stage('Build') {
        sh 'chmod +x gradlew'
        sh './gradlew package test'
    }
}
