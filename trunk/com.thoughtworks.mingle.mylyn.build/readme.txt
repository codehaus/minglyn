To set up the build, do the following:

0. Copy eclipse-sdk tar or zip file into test-sandbox/

1. extract eclipse-sdk from test-sandbox to "eclipse-target" in the checkout dir (MINGLYN_HOME)

2. Extract test-sandbox/mylyn-2.1-e3.3.zip and test-sandbox/mylyn-2.1-extras.zip into MINGLYN_HOME/mylyn-base/eclipse. These are minglyn dependencies, and would be required to run minglyn.

3. Checkout the mingle mylyn connector projects into a folder/workspace

4. Change the build.properties to point to the right folders

5. Rename build.developer.properties.template to build.developer.properties, change the "os", "ws", "arch" values according to your platform. See http://download.eclipse.org/eclipse/downloads/drops/R-3.3-200706251500/srcIncludedBuildInstructions.html#build_platforms for these values.

6. Modify the two "version" properties at the top of the build.properties file to match the versions of the plug-ins in your Eclipse installation.

7. When the build completes, navigate to mingle-mylyn-build/I.mingle-mylyn, you should find com.thoughtworks.mingle.mylyn-minglyn.zip which is your generated plugin.

