/*
 * Copyright 2015 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.tooltipNameShow.ui;

import com.google.common.collect.Lists;
import org.terasology.assets.management.AssetManager;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.input.InputSystem;
import org.terasology.logic.common.DisplayNameComponent;
import org.terasology.logic.inventory.InventoryClientSystem;
import org.terasology.logic.inventory.InventoryComponent;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.registry.CoreRegistry;
import org.terasology.registry.In;
import org.terasology.rendering.nui.ControlWidget;
import org.terasology.rendering.nui.UIWidget;
import org.terasology.rendering.nui.databinding.Binding;
import org.terasology.rendering.nui.databinding.ReadOnlyBinding;
import org.terasology.rendering.nui.layers.hud.CoreHudWidget;
import org.terasology.rendering.nui.layers.ingame.inventory.GetItemTooltip;
import org.terasology.rendering.nui.skin.UISkin;
import org.terasology.rendering.nui.widgets.*;
import org.terasology.world.BlockEntityRegistry;
import org.terasology.world.WorldProvider;

import java.util.List;

/**
 * @author Group of students of Portugal
 */
@RegisterSystem
public class TooltipNameShow extends CoreHudWidget implements ControlWidget {
    //private UIBox nameContainer;
    private UILabel blockNameT;
    private String nameTooltip;
    private long oldTime = System.currentTimeMillis()-3000;
    private final long timeBreak = 3;
    private long result;

    @In
    private WorldProvider worldProvider;
    @In
    private BlockEntityRegistry blockEntityRegistry;
    @In
    private InputSystem inputSystem;
    @In
    private AssetManager assetManager;

    public void enable(String name){
        oldTime = System.currentTimeMillis();
        nameTooltip = name;
    }

    private boolean isEnable(){
        long millis = System.currentTimeMillis();
        result = millis - oldTime;

        if(timeBreak > result/1000){
            return true;
        }
        return false;
    }

    @Override
    protected void initialise(){

        blockNameT = find("blockNameT", UILabel.class);

        blockNameT.bindText(new ReadOnlyBinding<String>() {
            @Override
            public String get() {
                if(isEnable()){
                    return nameTooltip;
                }
                return "";
            }
        });

    }

}
