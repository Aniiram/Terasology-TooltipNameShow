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
package org.terasology.tooltipNameShow.systems;

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.input.events.KeyEvent;
import org.terasology.logic.characters.CharacterComponent;
import org.terasology.logic.common.DisplayNameComponent;
import org.terasology.logic.inventory.InventoryUtils;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.network.ClientComponent;
import org.terasology.registry.CoreRegistry;
import org.terasology.registry.In;
import org.terasology.rendering.nui.NUIManager;
import org.terasology.tooltipNameShow.ui.TooltipNameShow;

@RegisterSystem
public class TooltipNameShowClientSystem extends BaseComponentSystem {

    @In
    private NUIManager nuiManager;

    public TooltipNameShowClientSystem() {

    }

    @Override
    public void preBegin() {
        nuiManager.getHUD().addHUDElement("TooltipNameShow:TooltipNameShow");
    }

    @ReceiveEvent(components = {ClientComponent.class}, priority = 100)
    public void keyEvent(KeyEvent event, EntityRef entity) {
        //CoreRegistry.get(Inventory.class);
        char key = event.getKeyCharacter();
        if (key  != '0' && key != '1' && key != '2' & key != '3' && key != '4' && key != '5'
                && key != '6' && key != '7' && key != '8' && key != '9'){
            return;
        }

        //THE IDENTITY OF THE ITEM COMPONENTS INSIDE THE TOOLBAR IS NOT RIGHT PROBABLY
        //BECAUSE OF THE PERMISSIONS

        String temp = "ERROR AHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH";
        EntityRef characterEntity = CoreRegistry.get(LocalPlayer.class).getCharacterEntity();
        CharacterComponent characterComponent = characterEntity.getComponent(CharacterComponent.class);

        if (characterComponent != null) {
            EntityRef item = InventoryUtils.getItemAt(characterComponent.movingItem, characterComponent.selectedItem);
            DisplayNameComponent displayNameComponent = item.getComponent(DisplayNameComponent.class);
            //temp = displayNameComponent.name;
        }else{
            temp = "------------...........----------------";
        }

        TooltipNameShow toolNS = (TooltipNameShow) nuiManager.getHUD().getHUDElement("TooltipNameShow:TooltipNameShow");
        toolNS.enable(temp);
    }}
