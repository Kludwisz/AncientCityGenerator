# AncientCityGenerator
Simulate the generation of Ancient Cities in Minecraft 1.19+ (tested for versions 1.19, 1.20.1, 1.20.6, 1.21.4)

# Credits
Thanks to Neil & KaptainWutax for their seedfinding libraries:  https://github.com/SeedFinding                                            
Thanks to profotoce59 for their VillageGenerator library:  https://github.com/profotoce59/VillageGenerator
Thanks to Gaider10 for massively optimizing the generator.

# Installation
`build.gradle`:                                                                                                                                                        

```
repositories {
	mavenCentral()
	maven { url "https://maven.seedfinding.com" }
	maven { url "https://www.jitpack.io" }
}
                                                                                      
dependencies {
	implementation('com.seedfinding:mc_math:1.171.0') { transitive = false }
	implementation('com.seedfinding:mc_seed:1.171.1') { transitive = false }
	implementation('com.seedfinding:mc_core:1.210.0') { transitive = false }
	implementation('com.seedfinding:mc_noise:1.171.1') { transitive = false }
	implementation('com.seedfinding:mc_reversal:1.171.1') { transitive = false }
	implementation('com.seedfinding:mc_biome:1.171.1') { transitive = false }
	implementation('com.seedfinding:mc_terrain:1.171.1') { transitive = false }
	implementation('com.seedfinding:mc_feature:1.171.9') { transitive = false }

	implementation('com.github.Kludwisz:AncientCityGenerator:1.3')
}
```
