#!/usr/bin/env groovy

@Library('ZomisJenkins')
import net.zomis.jenkins.Duga

node {
    stage('Checkout') {
        checkout scm
    }

    stage('Build') {
        sh './gradlew package test'
    }
}
