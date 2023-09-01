Pod::Spec.new do |spec|
    spec.name                     = 'adapt'
    spec.version                  = '1.0.0'
    spec.homepage                 = 'empty'
    spec.source                   = { :http=> ''}
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Adapt UI library'
    spec.vendored_frameworks      = 'build/cocoapods/framework/adapt.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '11.0'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':adapt',
        'PRODUCT_MODULE_NAME' => 'adapt',
    }
                
    spec.script_phases = [
        {
            :name => 'Build adapt',
            :execution_position => :before_compile,
            :shell_path => '/bin/sh',
            :script => <<-SCRIPT
                if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
                  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
                  exit 0
                fi
                set -ev
                REPO_ROOT="$PODS_TARGET_SRCROOT"
                "$REPO_ROOT/../gradlew" -p "$REPO_ROOT" $KOTLIN_PROJECT_PATH:syncFramework \
                    -Pkotlin.native.cocoapods.platform=$PLATFORM_NAME \
                    -Pkotlin.native.cocoapods.archs="$ARCHS" \
                    -Pkotlin.native.cocoapods.configuration="$CONFIGURATION"
            SCRIPT
        }
    ]
    spec.resources = ['build/compose/ios/adapt/compose-resources']
end