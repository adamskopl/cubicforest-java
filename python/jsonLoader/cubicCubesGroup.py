import imp
import cubicCube

class CubesGroup:
    """
    Class defining model's group of cubes.  

    Blender's equivalent: Group.
    """
    def __init__(self, name):
        # reload symbol table for Blender
        imp.reload(cubicCube)
        self.name = name
        # list of Cube elements
        self.cubes = list()
        
    def loadCubes(self, groupCubes):
        """
        Fill cubes list with objects from groupCubes list.
        """
        for groupCube in groupCubes:
            cube = cubicCube.Cube(groupCube.name)
            cube.setPos(groupCube.location)
            cube.setRot(groupCube.rotation_euler)
            self.cubes.append(cube)

    def toJson(self):
        groupDict = dict()
        cubesList = list()
        for c in self.cubes:
            cubeDict = c.toJson()
            cubesList.append(cubeDict)

        groupDict["name"] = self.name
        groupDict["cubes"] = cubesList
        return groupDict
