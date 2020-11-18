# PerlinHeightmap
Heightmap generator using Perlin noise.

Currently the branch titled 'double_conversion' is the most developed, including grassThreshold conditioning (grass will show on only suffieciently flat land).
The original intention of this branch is to allow smaller decrementation of height values than allowed with a [0,255] integer range. This is required for a yet undeveloped feature of eroding (and subsequent depositing) of land in a process of erosion. Combined with the already impleneted Perlin/fractal noise this will produce realistic approximations to landscapes.
