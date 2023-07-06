package org.pawfor710.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {

    private final List<Block> blocks;
    private final List<CompositeBlock> compositeBlocks;

    public Wall(List<Block> blocks, List<CompositeBlock> compositeBlocks) {
        this.blocks = blocks;
        this.compositeBlocks = compositeBlocks;
    }

    private List<Block> commonList() {
        List<Block> allBlocks = new ArrayList<>(blocks);
        allBlocks.addAll(compositeBlocks.stream()
                .flatMap(list -> list.getBlocks().stream())
                .toList());
        return allBlocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {

        return Optional.of(commonList().stream()
                        .filter(i -> i.getColor().equals(color))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Block with given color does not exist")));
    }

    @Override
    public List<Block> findBlockByMaterial(String material) {
        return commonList().stream()
                .filter(i -> i.getMaterial().equals(material))
                .toList();
    }

    @Override
    public int count() {
        return commonList().size();
    }
}





