import imp
import bpy
import cubicCubesGroup

class Model:
    """
    Class defining CubicForest model.

    Blender's equivalent: collection of Keyframes with Groups.
    """
    def __init__(self, name):
        # reload symbol table for Blender
        imp.reload(cubicCubesGroup)
        self.name = name
        # list of cubicCubesGroup.CubesGroup
        self.groups = list()

    def loadGroups(self):
        """
        Fill groups list with CubesGroup objects.
        """
        scene = bpy.data.scenes[0]
        sceneGroups = bpy.data.groups
        for sGroup in sceneGroups:
            groupName = sGroup.name
            groupCubes = sGroup.objects
            cubicGroup = cubicCubesGroup.CubesGroup(groupName)
            cubicGroup.loadCubes(groupCubes)
            self.groups.append(cubicGroup)

    def toJson(self, fStart, fEnd):
        scene = bpy.data.scenes[0]

        modelDict = dict()
        framesList = list()
        for frameNum in range(fStart, fEnd+1):
            scene.frame_set(frameNum)

            frameDict = dict()
            groupsList = list()
            for frameGroup in self.groups:
                groupDict = frameGroup.toJson()
                groupsList.append(groupDict)
            frameDict["number"] = frameNum
            frameDict["groups"] = groupsList
            framesList.append(frameDict)

        modelDict["name"] = self.name
        modelDict["frames"] = framesList
        
        return modelDict
