# Changelogs
## 2.2.0
- Update dependencies
- [StAPI] Fix crash on block drop
- [StAPI] Fix crash from block stats out of bound
- [StAPI] Disable forge multi-tessellator patch to fix rendering crashes
- Fix crash with Better Nether Beta (BNB)
- Fix crash with Always More Items (AMI)

## 2.1.0
- Update to MRAPI 1.22.1
- Fix all deprecation warnings in the codebase
- Fix reflection issues for most mods
- [StAPI] Update compat to 2.0-alpha.2.1
- [StAPI] Fix Dimensions portals not working
- [StAPI] Partial compatibility with ShockAhPI Dimensions
- [BHCreative] Show meta variants in generated tabs

## 2.0.1
- Fix crash on startup when using loader 0.15+
- Fix textures in non StAPI environment

## 2.0.0

### Added
- Now using MixinExtras `0.2.1+`
- Compatibility fixes for a lot of mods in vanilla setup
- Compatibility with StationAPI `2.0-alpha.1.1+`
- Compatibility fixes between StationAPI and ML mods
### Changed/Fix
- Converted most mixins to make use of MixinExtras features
- Improved ShockAhPI patch implementation
- Replaced most @Overwrites to ensure better compatibility with other mods
- Fix mod count always being 0 in F3 overlay.
- Fix StackOverflow when player takes damage and StAPI is installed.
- Fix tons of server side crashes.
- Hide Mod Options API and GuiAPI when there is nothing to show.
- Fix bucket use action being triggered twice.
- Update to loader `0.14.24+`
- Update MRAPI to `1.18.0+`

### ModLoader mods compatibility changes
- Aether
  - Ported Main Menu patch
  - Fixed TMI Compatibility
  - Fixed Aether Portal not working
- AetherMP
  - Fix crash
- Aether Expansion
  - Fix crash
- InfSprites
  - Fix crash
  - Redirect Tessellator calls to a custom compatible one
- OverrideAPI
  - Fix classloader crash
  - Fix compatibility with pre- and post- sarcasm versions.
- Better than Wolves
  - Ported patches
- HMI
  - Fix glitches
- Concrete
  - Fix crash
- SpawnEggs
  - Fix crash
- ReiMinimap
  - Fix crash
- BetaTweaks
  - Fix crash
- Somnia
  - Port patches
  - Fix crash
- AEI
  - Fix crash
- BetterBlocks
  - Fix crash because of Forge incompatibility
- IncredibleFungus
  - Port patch
- Nether Ores Remake
  - Fix crash
- EquivalentExchange (EE)
  - Fix crash
  - Fix textures not using Forge Atlas system correctly
- Buildcraft
  - Fix oil bucket texture

### StationAPI compatibility
#### Working
- Blocks
- Items
- Achievements + Achievement Screen
- Textures (mostly)
- Text/Translations
- Block Entities
- Entities
- Recipes
- [BHCreative] Creative Tab for each mod adding content
- [HMI] Furnace Recipes
#### Not working
- Dimensions
#### Unstable/Alpha features
- Converting worlds between Apron+Vanilla to Apron+StAPI
- Converting content from the following mods from their ML version to their StAPI version:
  - ClayMan -> ClaySoldier
  - StoneWall
#### Mod specify compatibility
- Fix Buildcraft pipes crashing when transfering items

## 1.0.0
Initial Release
