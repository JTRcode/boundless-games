package com.example.boundless.RotateTileGame;

public class HardCodeSetUps {
    //The set-up for first level, nulls represent that any Tile can be placed
    static TileEnum[][] game1_null =
                    {{TileEnum.I, TileEnum.L, null, null, null},
                    {TileEnum.L, TileEnum.X, null, null, null},
                    {TileEnum.I, null,TileEnum.T, TileEnum.L, null},
                    {TileEnum.T, null, TileEnum.I, TileEnum.L, TileEnum.X},
                    {TileEnum.X, TileEnum.I, TileEnum.L, null,TileEnum.L}};
    static TileEnum[][] game1 =
            {{TileEnum.I, TileEnum.L, TileEnum.I, TileEnum.I, TileEnum.I},
                    {TileEnum.L, TileEnum.X, TileEnum.L, TileEnum.T, TileEnum.I},
                    {TileEnum.I,TileEnum.I,TileEnum.T, TileEnum.L, TileEnum.L},
                    {TileEnum.T, TileEnum.L, TileEnum.I, TileEnum.L, TileEnum.X},
                    {TileEnum.X, TileEnum.I, TileEnum.L, TileEnum.X,TileEnum.L}};
}
