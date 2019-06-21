package dungeon;

import java.util.HashMap;
import java.util.Map;

public class AttackFactory 
{
	public enum AttackType
	{
		Standard, CrushingBlow, Suprise, HealSelf, Spell, Bash, Snap, OP, MonsterStandard, MonsterSpecial, MonsterAdvanced;
	}
	
	private Map<AttackType, Attack> attackPool = new HashMap<AttackType, Attack>();
	
	public Attack getAttack(AttackType type)
	{
		Attack atk = attackPool.get(type);
		
		if(atk == null)
		{
			atk = makeAttack(type);
			attackPool.put(type, atk);
		}
		
		return atk;
	}
	
	private Attack makeAttack(AttackType type)
	{
		if(type == null)
			return null;
			
		switch(type)
		{
			case Standard:
				return new AttackStandard();
				
			case CrushingBlow:
				return new AttackCrushingBlow();
				
			case Suprise:
				return new AttackSuprise(getAttack(AttackType.Standard));
				
			case HealSelf:
				return new AttackHealSelf();
				
			case Spell:
				return new AttackSpell();
				
			case Bash:
				return new AttackBash();
				
			case Snap:
				return new AttackSnap();
				
			case OP:
				return new AttackOP();
				
			case MonsterStandard:
				return new AttackMnstrStandard();
				
			case MonsterSpecial:
				return new AttackMnstrSpecial();
				
			case MonsterAdvanced:
				return new AttackMnstrAdvanced();
				
			default:
				return new AttackStandard();
		}
	}
}
